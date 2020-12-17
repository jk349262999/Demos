# Markdown功能列表演示

## 标题

### 标题H3

#### 标题H4

##### 标题H5
----

### 列表 Lists

#### 无序列表（减号）Unordered Lists (-)

- 列表一
- 列表二
- 列表三

#### 无序列表（星号）Unordered Lists (*)

* 列表一
* 列表二
* 列表三

#### 无序列表（加号和嵌套）Unordered Lists (+)

+ 列表一
+ 列表二
    + 列表二-1
    + 列表二-2
    + 列表二-3
+ 列表三
    * 列表一
    * 列表二
    * 列表三

#### 有序列表 Ordered Lists (-)

1. 第一行
2. 第二行
3. 第三行

----

### 字符效果和横线等

~~删除线~~ <s>删除线（开启识别HTML标签时）</s>

*斜体字*      _斜体字_

**粗体**  __粗体__

***粗斜体*** ___粗斜体___

上标：O<sup>2</sup>，下标：X<sub>2</sub>

**缩写(同HTML的abbr标签)**
> 即更长的单词或短语的缩写形式，前提是开启识别HTML标签时，已默认开启

The <abbr title="Hyper Text Markup Language">HTML</abbr> specification is maintained by
the <abbr title="World Wide Web Consortium">W3C</abbr>.

### 引用 Blockquotes

> 引用文本 Blockquotes 引用的行内混合 Blockquotes
> 引用：如果想要插入空白换行`即<br />标签`，在插入处<br/>先键入两个以上的空格然后回车即可;

### 图片与链接 Links

![图片](https://pandao.github.io/editor.md/images/logos/editormd-logo-180x180.png "Pandao editor.md")

[普通链接](https://www.mdeditor.com/)

直接链接：<https://www.mdeditor.com>

[mailto:test.test@gmail.com](mailto:test.test@gmail.com)

### 多语言代码高亮 Codes

#### 行内代码 Inline code

执行命令：`npm install marked`

#### 缩进风格

即缩进四个空格，也做为实现类似 `<pre>` 预格式化文本 ( Preformatted Text ) 的功能。

    <?aaa
        bbb "Hello world!";
    ?>

预格式化文本： | First Header | Second Header | | ------------- | ------------- | | Content Cell | Content Cell | | Content
Cell | Content Cell |

#### HTML 代码 HTML codes

```html
<!DOCTYPE html>
<html>
    <head>
        <mate charest="utf-8" />
        <meta name="keywords" content="Editor.md, Markdown, Editor" />
        <title>Hello world!</title>
        <style type="text/css">
            body{font-size:14px;color:#444;font-family: "Microsoft Yahei", Tahoma, "Hiragino Sans GB", Arial;background:#fff;}
            ul{list-style: none;}
            img{border:none;vertical-align: middle;}
        </style>
    </head>
    <body>
        <h1 class="text-xxl">Hello world!</h1>
        <p class="text-green">Plain text</p>
    </body>
</html>

#### JS代码
```javascript
function test() {
	console.log("Hello world!");
}
```

