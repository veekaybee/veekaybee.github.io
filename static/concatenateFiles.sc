"com.lihaoyi" %% "os-lib" % "0.7.8"


val wd = os.pwd / "_posts"
val sd = os.Path("/Users/vicki/IdeaProjects/scalding/scalding-repl")

// Concatentates all the files
os.write.over(
  wd / "posts.md",
  os.list(wd).filter(_.ext == "md").map(os.read)
)


def remove(content: String): String = {

// Remove horizontal rules (stripListHeaders conflict with this rule, which is why it has been moved to the top)
var output = content.replaceAll("(?m)^(-\\s*?|\\*\\s*?|_\\s*?){3,}\\s*$", "")

output = output.replaceAll("(?m)^([\\s\\t]*)([\\*\\-\\+]|\\d+\\.)\\s+", "$1")

// Remove HTML tags
output.replaceAll("<[^>]*>", "")
  // Remove setext-style headers
  .replaceAll("^[=\\-]{2,}\\s*$", "")
  // Remove footnotes?
  .replaceAll("\\[\\^.+?\\](\\: .*?$)?", "")
  .replaceAll("\\s{0,2}\\[.*?\\]: .*?$", "")
  // Remove images
  .replaceAll("\\!\\[(.*?)\\][\\[\\(].*?[\\]\\)]", "")
  // inline link
  .replaceAll("\\[(.*?)\\][\\[\\(].*?[\\]\\)]", "$1")
  // Remove blockquotes
  .replaceAll("^\\s{0,3}>\\s?", "")
  // Remove reference-style links?
  .replaceAll("^\\s{1,2}\\[(.*?)\\]: (\\S+)( \".*?\")?\\s*$", "")
  // Remove atx-style headers
  .replaceAll("(?m)^(\\n)?\\s{0,}#{1,6}\\s+| {0,}(\\n)?\\s{0,}#{0,} {0,}(\\n)?\\s{0,}$", "$1$2$3")
  // Remove emphasis (repeat the line to remove double emphasis)
  .replaceAll("([\\*_]{1,3})(\\S.*?\\S{0,1})\\1", "$2")
  .replaceAll("([\\*_]{1,3})(\\S.*?\\S{0,1})\\1", "$2")
  // Remove code blocks
  .replaceAll("(`{3,})(.*)", "")
  // Remove inline code
  .replaceAll("`(.+?)`", "$1")
  // Replace two or more newlines with exactly two? Not entirely sure this belongs here...
  .replaceAll("\\n{2,}", "\n\n")
  .replaceAll("layout:", "")
  .replaceAll("title:", "")
  .replaceAll("---", "")
  .replaceAll(">", "")
}

// Open output markdown file and perform some syntax changes on it

os.write.over(
  wd / "posts.md",
  remove(os.read(wd / "posts.md"))
)

// Process for Scalding
os.move.over( wd/ "posts.md", wd /"posts.txt")

os.move.over( wd/ "posts.txt", sd / "posts.txt")

