+++
card = 'summary'
creator = '@vboykis'
date = '2024-11-09'
site = '@vboykis'
title = "Why are we using LLMs as calculators"
description = 'For AGI'
+++

We [keep trying to get LLMs to do math](https://www.reddit.com/r/singularity/comments/122ilav/why_is_maths_so_hard_for_llms/). We want them [to count the number of "rs" in strawberry](https://community.openai.com/t/incorrect-count-of-r-characters-in-the-word-strawberry/829618), to perform [algebraic reasoning](https://arxiv.org/abs/2303.05398), [do multiplication](https://news.ycombinator.com/item?id=30309302), and [to solve math theorems.](https://mathstodon.xyz/@tao/113132502735585408) 

<img width="758" alt="Screenshot 2024-11-04 at 10 19 23 AM" src="https://github.com/user-attachments/assets/29ce6ef4-f92a-4397-9eda-fb6ccf202590">

A [recent experiment particularly](https://x.com/yuntiandeng/status/1836114401213989366) piqued my interest. Researchers used OpenAI's new [4o model](https://openai.com/index/hello-gpt-4o/) to solve multiplication problems by using the prompt:

```shell
Calculate the product of x and y. Please provide the final answer in the format: 
Final Answer: [result]
```

<img width="478" alt="llm_calc" src="https://github.com/user-attachments/assets/72f1f906-20b6-4d5d-880a-da1065e15f39">

These models are generally [trained for natural language tasks](https://arxiv.org/abs/2204.07705), particularly text completions and chat. 

<img width="848" alt="Screenshot 2024-11-04 at 10 22 23 AM" src="https://github.com/user-attachments/assets/1ef1bc9d-408d-40ea-b2ec-073120785ac6">

So why are we trying to get these enormous models, good for natural text completion tasks like summarization, translation, and writing poems, to multiply three-digit numbers and, what's more, attempt to return the results as a number? 

Two reasons: 
1. Humans always try to use any new software/hardware we invent to do calculation
2. We don't actually want them to do math for the sake of replacing calculators, we want to understand if they can reason their way to AGI.
# Computers and counting in history

In the history of human relationships with computers, we've always wanted to count large groups of things because [we're terrible at it](https://en.wikipedia.org/wiki/The_Magical_Number_Seven,_Plus_or_Minus_Two). Initially we used our hands - or others' - in the Roman empire, administrators known as _calculatores_ and  slaves known as [calculones](https://kartsci.org/kocomu/computer-history/history-abacus-ancient-computing/) performed household accounting manually. 

<img width="1159" alt="Screenshot 2024-11-04 at 10 30 11 AM" src="https://github.com/user-attachments/assets/bbe7a45f-86a5-40f3-abba-ec635ce7c93f">

Then, we started inventing calculation lookup tables. After the French Revolution, the French Republican government switched to the metric system in order to collect property taxes. In order to perform these calculations, it hired human computers to do the conversions by creating large tables of logarithms for decimal division of angles, [Tables du Cadastre](https://inria.hal.science/inria-00543946/document). This system was never completed and eventually scrapped, but it inspired Charles Babbage to do his work on machiens for calculation along with Ada Lovelace, which in turn kicked off the modern era of computing. 

UNIVAC, one of the first modern computers, was used by the Census Bureau in [population counting.](https://www.census.gov/about/history/bureau-history/census-innovations/technology/univac-i.html)

The nascent field of artificial intelligence developed jointly in line with the expectation that machines should be able to replace humans in computation through historical developments like the Turing Test, the [Dartmouth Artificial Intelligence Conference](https://spectrum.ieee.org/dartmouth-ai-workshop) and [Arthur Samuel's checkers demo](https://www.ibm.com/history/early-games). 

Humans have been inventing machines to mostly do math for milennia, and it's only recently that computing tasks have moved up the stack from calculations to higher human endeavors like writing, searching for information, and shitposting.  So naturally, we want to use LLMs to do the thing we've been doing with computers and software all these years.

# Making computers think

Second, we want to understand if LLMs can "think." There is no one definition of what "thinking" means, but for these models in particular, [we are interested to see](https://arxiv.org/abs/2212.10403) if they can work through a chain of steps to come to an answer about logical things that are easy for humans, as an example:

> all whales are mammals, all mammals have kidneys; therefore, all whales have kidneys

One way humans reason is through performing different kinds of math: arithmetic, solving proofs, and reasoning through symbolic logic.  The underlying question in artificial intelligence is whether machines can reason outside of the original task we gave them. For large language models, the ask is whether they can move from summarizing first a book if they were trained for books, to a movie script plot, to finally, summarizing what you did all day if you pass it a bunch of documents about your activity. So, it stands to reason that if LLMs can "solve" math problems, they can achieve AGI. 

There are approximately seven hundred million benchmarks to see if LLMs can reason ([here's an example](https://www.llm-reasoning-benchmark.com/), and [here's another one](https://arxiv.org/abs/2307.13692). Even since I started this draft yesterday, [a new one came out.](https://epochai.org/frontiermath/the-benchmark)  

Since it's hard to define what "reasoning" or "thinking" means, the benchmarks try to proxy to see if models can answer the same questions we give to humans in settings such as university tests and compare the answers between human annotators generating ground truth and inference run on the model. 

These types of tasks make up a [large number of LLM benchmarks that are popular on LLM leaderboards.](https://huggingface.co/spaces/open-llm-leaderboard/open_llm_leaderboard).

# How calculators work

However, evaluating how good LLMs are at calculation doesn't take into account a critical component: the way that calculators arrive at their answer is radically different from how these models work.  A calculator records the button you pressed and converts it to a binary representation of those digits. Then, it stores those number in memory registers until you press an operation key. For basic hardware calculators, the machine has built-in operations that perform variations of addition on the binary representation of the number stored in-memory: 

        + addition is addition, 
        + subtraction is performed via two's complement operations, 
        + multiplication is just addition, and 
        + division is subtraction

In software calculators, [the software takes user keyboard input](https://www.bloomberg.com/graphics/2015-paul-ford-what-is-code/), generates a scan code for that key press, encodes the signal, converts it to character data, and uses an encoding standard to convert the key press to a binary representation. That binary representation is sent to the application level, which now starts to work with the variable in the programming language the calculator uses, and performs operations on those variables based on [internally-defined methods for addition, subtraction, multiplication, and division.](https://gitlab.gnome.org/GNOME/gnome-calculator/-/blob/main/lib/number.vala?ref_type=heads#L587). 

Software calculators can grow to be fairly complicated with the addition of graphing operations and calculus, but usually have a standard collected set of methods to follow to perform the actual calcuation. As a fun aside, [here's a great piece](https://www.pcalc.com/mac/thirty.html) on what it was like to build a calculator app Back In The Day. 

<img width="743" alt="Screenshot 2024-11-03 at 8 55 18 AM" src="https://github.com/user-attachments/assets/af7bc287-8ff8-4546-9366-614ed6df6e67">

The hardest part of the calculator is writing the logic for representing numbers correctly and creating manual classes of operations that cover all of math's weird corner cases. 

However, To get an LLM to add "2+2", we have a much more complex level of operations. Instead of a binary calculation machine that uses small, simple math business logic to derive an answer based on addition, we create an enormous model of the entire universe of human public thought and try to reason our way into the correct mathematical answer based on how many times the model has "seen" or been exposed to the text "2+2" in written form. 

We first train a large language model to answer questions by: 

![d8e7f5ec-c333-4890-8430-7f73fe9e89fa_1588x386](https://github.com/user-attachments/assets/87e4ee63-7270-4fcf-89da-45769b7aba53)

[Source](https://magazine.sebastianraschka.com/p/new-llm-pre-training-and-post-training)

1. [Gathering and deduplicating](https://magazine.sebastianraschka.com/p/new-llm-pre-training-and-post-training) an enormous amount of large-scale, clean internet text
2. We then train the model by feeding it the data and asking it, at a very simplified level, to predict the next word in a given sentence. We then compare that prediction to the baseline sentence and adjust a loss function. An attention mechanism helps guide the prediction by keeping a context map of all the words of our vocabulary (our large-scale clean internet text.)
3. Once the model is trained initially to perform the task of text completion, we perform [instruction fine-tuning](https://arxiv.org/abs/2308.10792), to more closely align the model with the task of performing a summarization task or following instructions. 
4. Finally, the model is aligned with human preferences with RLHF. [This process](https://huggingface.co/blog/rlhf)  involves collecting a set of questions with human responses, and having human annotators rank the response of the model, and then feeding those ranks back into the model for tuning.  
5. Finally, we stand up that artifact (or have it accessable as a service.) The artifact is [a file or a collection of files](https://vickiboykis.com/2024/02/28/gguf-the-long-way-around/) that contain the model architecture and weights and biases of the model generated from steps 2 and 3. 

Then, when we're ready to query our model. This is the step that most people take to get an answer from an LLM when they hit a service or run a local model, equivalent to opening up the calculator app. 

1. We write "What's 2 +2" into the text box.
2. This natural-language query [is tokenized](https://cybernetist.com/2024/10/21/you-should-probably-pay-attention-to-tokenizers/).  Tokenization is the process of first converting our query into a string of words that the model uses as the first step in performing numerical lookups.
2. That text is then embedded in the context of the model's vocabulary by converting each word to an embedding and then creating an embedding vector of the input query.
3. We then passing the vector to the model's encoder, which stores the relative position of embeddings to each other in the model's vocabulary
4. Passing those results to the attention mechanism for lookup, which compares the similarity using various metrics of each token and position with every other token in the reference text (the model). This happens many times in multi-head attention architectures. 
5. Getting results back from the decoder. What is returned from the decoder is a [set of tokens and the probability of those tokens.](https://huggingface.co/docs/transformers/en/llm_tutorial).

First, we need to generate the first token that all the other tokens are conditioned upon.  However, afterwards, [returning probablities takes many forms](https://huggingface.co/blog/how-to-generate): namely search strategies like greedy search and and sampling, most frequently top-k sampling, the method originally used by GPT-2. Depending on which strategy you pick and what tradeoffs you'd like to make, you will get [slightly different answers of resulting tokens selected from the model's vocabulary.](https://gist.github.com/kalomaze/4473f3f975ff5e5fade06e632498f73e)   

Finally, even after this part, to ensure that what the model outputs is an actual number, we could do a number of different guided generation strategies to ensure we get ints or longs as output from [multiplication, addition, etc.](https://dottxt-ai.github.io/outlines/latest/welcome/) 

So this entire process, in order to add "what is 2+2", we do a non-deterministic a lookup from an enormous hashtable that contains the sum of public human knowledge we've seen fit to collect for our dataset, then we squeeze it through the tiny, nondeterministic funnels of decoding strategies and guided generation to get to an answer from a sampled probability distribution. All of this includes large swaths of actual humans in the loop guiding the model.

![untitled@2x(1)](https://github.com/user-attachments/assets/8bd85044-8583-48dd-b620-f8f13a134d18)

And, all of this, only to get an answer that's right only some percent of the time, not consistent across all model architectures and platforms and in many cases has to be coaxed out of the model using techniques like chain of thought. 

As an example, here's an aswer I've tried on OpenAI, Claude, Gemini, and locally using Mistral via llamafile and ollama:

<img width="567" alt="Screenshot 2024-11-02 at 7 30 49 PM" src="https://github.com/user-attachments/assets/7be40c7f-8f7b-48db-9ad9-80c421e3c05c">
Claude Sonnet 3.5

<img width="841" alt="Screenshot 2024-11-02 at 7 31 53 PM" src="https://github.com/user-attachments/assets/ec024d0d-3d58-4bab-9052-a31c91a0bc62">
Gemini 1.5 Flash

<img width="670" alt="Screenshot 2024-11-02 at 7 32 29 PM" src="https://github.com/user-attachments/assets/ecd53bb1-063a-477d-9969-877cfa3eb35c">
OpenAI ChatGPT GPT-4 Turbo

<img width="908" alt="Screenshot 2024-11-09 at 11 21 45 PM" src="https://github.com/user-attachments/assets/3fd6bdaf-dc08-4208-959f-46df356bc4d9">
Llamafile Mistral 7-B Instruct 2

<img width="788" alt="Screenshot 2024-11-09 at 11 24 57 PM" src="https://github.com/user-attachments/assets/986371b3-6703-41a9-bdf8-ea74680149ed">
Ollama Mistral

If you ask any given calculator what 2+2 is, you'll always get 4. This doesn't work with LLMs, even when it's variations of the same model, much less different models hosted across different service providers and in different levels of quantization, different sampling strategies, mix of input data, and more.   

## Why are we even doing this? 

From a user perspective, this is absolutely a disastrous violation of Jakob's Law of Ux, which states that people [expect the same kind of output](https://vickiboykis.com/2024/05/06/weve-been-put-in-the-vibe-space/) from the same kind of interface. 

However, when you realize that the goal is, as [Terrence Tao notes](https://mathstodon.xyz/@tao/113132502735585408), to get models to solve mathematical theorems, it makes more sense, although all these models are still very far from actual reasoning. 

I'd love to see us spend time more understanding and working on the practical uses [he discusses](https://unlocked.microsoft.com/ai-anthology/terence-tao/): drafts of documents, as ways to check understanding of a codebase, and of course, [generating boilerplate Pydantic models for me personally.](https://vickiboykis.com/2023/02/26/what-should-you-use-chatgpt-for/). 

But, this is the core tradeoff between practicality and research: do we spend time on Pydantic now because it's what's useful to us at the moment, or do we try to get the model to write the code itself to the point where we don't even need Pydantic, or Python, or programming languages, and can write natural language code, backed by mathematical reasoning? 

If we didn't spend time on the second, we never would have gotten even to GPT-2, but the question is, how much further can we get? I'm not sure, but I personally am still not using LLMs for tasks that can't be verified or for reasoning, or for counting Rs.  

---
Further Reading: 

* [Artificial General Intelligence by Julian Togelius](https://mitpress.mit.edu/9780262549349/artificial-general-intelligence/)
* [Empire of the Sum: The Rise and Reign of the Pocket Calculator by Keith Houston](https://wwnorton.com/books/9780393882148)
* [Dartmouth AI Workshop Original Proposal](http://jmc.stanford.edu/articles/dartmouth/dartmouth.pdf)
