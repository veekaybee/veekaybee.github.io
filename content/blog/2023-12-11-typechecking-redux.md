+++
card = 'summary'
creator = '@vboykis'
date = '2023-12-11'
site = '@vboykis'
title = "Why if TYPE_CHECKING?"
description = 'Typechecking is brittle yet important'
+++

I saw [this tweet](https://twitter.com/charliermarsh/status/1733865143694487769) over the weekend and wanted to dive into the fundamental question behind this: Given this potential error, why do we use conditional imports at all, or, more specifically, when might we use this pattern? 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/038f3695-4651-45e6-b47c-47ec25da9690">}}

The TL;DR is that we use this pattern to hedge between the differences in typechecking enforced by mypy and typechecking as it happens at runtime, particularly when we have large sets of custom classes that depend on each other and could result in circular dependencies. 

The result of the deep dive is that I now no longer trust anything in the Python typing ecosystem. Or, [as Max writes](https://bernsteinbear.com/blog/typed-python/), 

> The core thesis is: types are very broad hints and they are sometimes lies.

This is not inherently the fault of the Python ecosystem. It is the result of [trying to gradually type](https://wphomes.soic.indiana.edu/jsiek/what-is-gradual-typing/) a dynamically-typed language, a language that, due to its success, now needs to encompass a broad swatch of different use-cases that need both clarity of expression for the end programmer, as well as performance in high-performance distributed computing applications. It's not easy. 

First, [a quick primer on Python type hints.](https://vickiboykis.com/2019/07/08/a-deep-dive-on-python-type-hints/)

 > A long time ago, in a galaxy far, far, away, people doing math by hand realized that if they labeled numbers or elements of equations by “type”, they could reduce the amount of logic issues they had when doing math proofs against those elements.

  > Since in the beginning computer science was, basically, doing a lot of math by hand, some of the principles carried over, and a type system became a way to reduce the number of bugs in your program by assigning different variables or elements to specific types.

> Python is dynamically-typed, which means it only checks the types of the variables you specified when you run the program. As we saw in the sample piece of code, you don’t have to plan out the types and memory allocation beforehand.

> When CPython is building the program, how does it know which types the variables are if we don’t specify them? It doesn’t. All it knows is that the variables are objects. Everything in Python is an Object, until it’s not (i.e. it becomes a more specific type), that is when we specifically check it.

> For types like strings, Python assumes that anything with single or double quotes around it will be a string. For numbers, Python picks a number type. If we try to do something to that type and Python can’t perform the operation, it’ll tell us later on.
Let's start at the beginning. When we write a program, we have two places we can check it for correctness: at compile time, and at runtime. 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/69105b9b-af35-4f46-bc43-49daf31f8da7">}}

However, Python doesn't have the compile-time check, because it's an interpreted language that is dynamically-typed, which means its only real place to check is at runtime, i.e. when we type `python my_program.py`

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/2945fed1-b49a-4ba2-b2ab-9c8f6e02c6f0">}}

What do we do to try to find errors before we run the program? We run [type checking](https://stackoverflow.com/a/61548894), which does everything except execute the actual program code: 

> The type checker never executes your code: instead, it analyzes it. Type checkers are implemented in pretty much the same way compilers are implemented, minus the "generate bytecode/assembly/machine code" step. This means your type checker has more strategies available for resolving import cycles (or cycles of any kind) than the Python interpreter will have during runtime since it doesn't need to try blindly importing modules.

So the type checker, mypy in our case, is broad and smart but slow and has no execution power (we don't even have to run it as a pre-lint step, [it's completely optional in any codebase](https://mypy-lang.org/)!), and the Python implementation that runs when the code is run, is fast, but blunt and limited in its scope. It looks like this: 

{{< figure  width="600" src="https://github.com/veekaybee/veekaybee.github.io/assets/3837836/fa152460-7d1e-4340-8e5d-396d21248494">}}

Another way to think about this is as a funnel for errors where the top of the funnel is MyPy and the bottom is CPython. (an unfortunate side effect of working in recommendations is that everything starts to look like a filter and funnel.)

What are the implications of this? When Type Checking is happening, that [TYPE_CHECKING flag](https://docs.python.org/3/library/typing.html#constant) is set to True. Otherwise, at run-time, this variable is False. 

```python
# A special constant that is assumed to be True by 3rd party static type checkers. It is False at runtime.
TYPE_CHECKING = False
```

One of the things we can do when type checking is happening is include conditional imports. Conditional imports are packages that are only imported when we're doing typchecking. Why would we want to do this? [To avoid circular references.](https://stackoverflow.com/questions/61545580/how-does-mypy-use-typing-type-checking-to-resolve-the-circular-import-annotation) If you have two classes that depend on each other, mypy will try to go through and sequentially find class by class and substitute null references with placeholders. 

The example in the tweet doesn't contain this:  

```python
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from collections.abc import Sequence

def func(value: Sequence[int]) -> None:
    pass

```

But [this one does](https://github.com/python/mypy/issues/4440): 

```python

--------ClassA.py---------
import classB

class A:
    def __init__(self, B_obj: B) -> None:
        pass
---------------------------

-------ClassB.py---------
import classA

class B:
    def __init__(self, A_obj: A) -> None:
        pass
---------------------------
```

A and B reference each other, but the first time through the loop, B is not instantiated yet and so cannot be referenced. At run-time this would simply be ignored, because, remember, Python is dynamically-typed so it doesn't care what the type is at run-time. You could even make the type a string it will still run: 

```python
from typing import TYPE_CHECKING

if TYPE_CHECKING:
    from collections.abc import Sequence

def func(value: 'Sequence[int]') -> None:
    pass
```

But MyPy will go through and try to find all the dependencies in the second loop. 

> mypy does is basically start by analyzing your code module-by-module, keeping track of each new class/new type that's being defined. During this process, if mypy sees a type hint using a type that hasn't been defined yet, substitute it with a placeholder type.

> Once we've finished checking all the modules, check and see if there are still any placeholder types floating around. If so, try re-analyzing the code using the type definitions we've collected so far, replacing any placeholders when possible. We rinse and repeat until there are either no more placeholders or we've iterated too many times.

In order to capture this level of meticulousness at the level of the typechecker, we do [conditional imports](https://www.stefaanlippens.net/circular-imports-type-hints-python.html).

So, what does this all mean in a TL;DR: 

1. Python type-checking is very strongly decoupled from the actual runtime code and is largely a user-level mechanism to make reading code much easier.
2. Typechecking by mypy is more meticulous than the checking that happens at runtime, so we have to do hacks in order to get code that would otherwise run to pass typechecking, particularly when working with circular dependencies. 
3. Python doesn't care about types at runtime, so we do another hack (types as strings) to make sure the code gets interpolated correctly using forward references [during typechecking and ignored at runtime.](https://peps.python.org/pep-0484/#forward-references). We can do this because MyPy will attempt to parse strings into their respective types. 
4. Be very careful when using type annotations. You should use them! But also understand that they are also built on some very loose guarantees.  












