[TOC]

## JavaScript

+   this关键字

    +   在方法中，this表示该方法所属的对象
    +   如果单独使用，this表示全局对象
    +   在函数中，this表示全局对象
    +   在函数中，在严格模式下，this是未定义的
    +   在事件中，this表示接收事件的元素

+   undefined是全局对象的一个属性，也就是说，它是全局作用域的一个变量，undefined的最初值就是原始数据类型undefined。ES5之后的标准中，规定了全局变量下的undefined值为只读。不可改写的，但是局部变量中依然可以对之进行改写。而void 0无论什么时候都是返回undefined。这样来看，使用void 0代替undefined比较稳妥，不会出错。

    +   任何生命变量时没有提供一个初始值，都会有一个为undefined的默认值
    +   当试图访问一个不存在的对象属性或数组项时，返回一个undefined的值
    +   如果省略了函数的返回语句，返回undefined
    +   函数调用时未提供的值，结果将为undefined参数值
    +   void操作符也可以返回一个undefined值。像Underscore的库使用它作为一个防御式的类型检查，因为它是不可变的，可以在任何上下文依赖返回undefined
    +   undefined是一个预定义的全局变量（不像null关键字）初始化为undefined值

+   null

    +   通常用作一个空引用，一个空对象的预期，就像一个占位符。typeof这种行为已经被确认为一个错误，虽然提出了修正，处于后兼容的目的，这一点已经保持不变。这就是为什么JavaScript环境从来没有设置一个值为null；它必须以编程的方式完成。
    +   使用null的情况
        +   DOM，它是独立于语言的，不属于ECMAScript规范的范围。因为它是一个外部API，试图获取一个不存在的元素返回一个null值，而不是undefined。
        +   如果你需要给一个变量或属性指定一个不变值，将它传递给一个函数，或者从一个函数返回null，null几乎总是最好的选择。
        +   JavaScript使用undefined，并且程序员应该使用null。
        +   通过分配null值，有效地清除引用，并假设对象没有引用其他代码，指定垃圾收集，确保回收内存。

+   Object.prototype.toString调用过程

    +   如果值是undefined，返回 **[object Undefined]**
    +   如果值是null，返回 **[object Null]**
    +   让O作为调用ToObject同时传递this值作为参数的结果值
    +   让class是O的内部属性[Class]的值
    +   返回的结果连接三个字符串[object, class, 和]的结果的字符串值

+   cookie的一个属性secure，用来表明cookie的值以何种形式通过网络传递。cookie默认是以不安全的形式（通过普通的，不安全的HTTP连接）传递的。而一旦cookie被标识为安全的，那就只能当浏览器和服务器通过HTTPS或其他的安全协议连接的时候才能传递他。

    +   由于cookie的名/值中的值是不允许包含分号、逗号、空白符。因此，在存储前一般可以采用JavaScript核心的全局函数encodeURIComponent()对值进行编码。相应的，读取cookie值的时候，需要采用decodeURIComponent()函数解码。
    +   以简单的名/值对形式存储的cookie数据有效期只在当前浏览器的会话内，一旦用户关闭浏览器，cookie数据就丢失了。如果想要延长cookie的有效期，就需要设置max-age属性来指定cookie的有效期(单位是秒)。

+   事件冒泡是微软的概念，表示事件按照从最特定的事件目标到最不特定的事件目标的顺序触发。addEventListener最后一个参数用**false**，默认是**false**，即默认使用**事件冒泡**模式

    +   事件捕获是网景的概念，表示事件从最不精确的对象开始触发，然后到最精确。addEventListener最后一个参数用**true**

+   jQuery使用同一个方法既当getter用又当setter用，而不是定义一对方法。如果传入一个新值给该方法，则它设置此值；如果没指定值，则它返回当前值。

+   XMLHttpRequest.withCredentials 属性是一个Boolean类型，它指示了是否该使用类似cookies, authorization headers（头部授权）或者TLS客户端证书这一类资格证书来创建一个跨站点访问控制。**在同一个站点下使用withCredentials是无效的，即永远不会影响到同源请求**

    +   此外，这个指示也会被用作**响应中cookies被忽视**的标识，默认值是false

    +   如果在发送来自其他域的XMLHttpRequest请求之前，未设置withCredentials为true，那么就不能为它自己的域设置cookie值。而通过设置withCredentials为true获得的第三方cookies，将会依旧享受同源策略。因此不能被通过document.cookie或者从头部相应请求的脚本访问。

        >   不同域下的XMLHttpRequest响应，不论其Access-Control-Header设置什么值，都无法为它自身站点设置cookie值，除非它在请求之前将withCredentials设置为true

+   load事件直到文档和所有图片加载完毕时才发生。然而，在文档完全解析之后，但在所有图片全部加载完毕之前开始运行脚本通常是安全的，所以如果基于**load**发生之前的事件触发脚本会提升Web应用的启动时间。

    +   当文档加载解析完毕，且所有**延迟deferred脚本**都执行完毕时会触发**DOMContentLoaded**事件，此时图片和**异步async脚本**可能还在加载。但是文档已经为操作准备就绪了。
    +   **document.readyState**属性随着文档的加载过程而变，在IE中，每次状态改变都伴随着Document对象上的readystatechange事件，当IE接收到**complete**状态时，使用这个事件来做判断是可行的。但它**仅在load事件之前**立即触发，所以目前尚不清楚监听readystatechange取代load会带来多大好处。

+   设置visibility属性为hidden使得元素不可见，但是在文档布局中仍保留了它的空间。类似的元素可以重复隐藏和显式而不改变文档布局。

    +   如果元素的display设置为none，在文档布局中不再给他分配空间，它各边的元素会合拢，就当他从来不存在。

+   标准模型：width和height样式属性给定内容区域的尺寸，并且不包含**内边距**和**边框**

    +   怪异模型：width和height属性是包含**内边距**和**边框宽度**的

+   文档可编辑

    +   将Document对象的designMode属性设置为字符串on是的整个文档可编辑，设置off恢复为只读文档
    +   设置任何标签的HTML contenteditable属性。或者设置对应元素的JavaScript contenteditable属性

+   HTML5文档中，任意以**data-**为前缀的小写的属性名称都是合法的。这些数据集属性将不会对其元素的表现产生影响，它们定义了一种标准的、附加额外数据的方法，并不是在文档合法性上做出让步。

    +   HTML5还在Element对象上定义了dataset属性。该属性指代一个对象，它的各个属性对应于去掉前缀的**data-**属性，因此dataset.x应该保存data-x属性的值。带连字符的属性对应于驼峰命名法属性名：data-jquery-test属性就变成dataset.jqueryTest属性。

+   CSS定义了**:first-line**和**:first-letter**等伪元素。在CSS中，它们匹配文本节点的一部分而不是实际元素。如果和querySelectorAll()或querySeletor()一起使用，它们是不匹配的。而且，很多浏览器会拒绝返回**:link**和**:visited**等伪类的匹配结果，因为这会泄露用户的浏览历史记录。

+   Window对象是客户端JavaScript的全局变量。但是从技术上讲，并不是这样的。Web浏览器每次向窗口或窗体中载入新的内容时，他都会开始一个新的JavaScript执行上下文，包含一个新创建的全局对象。但是当多个窗口或窗体在使用时，有一个重要的概念，尽管窗体或窗口载入了新的文档，但是引用窗体或窗口的Window对象还仍然是一个有效的引用。

    +   客户端JavaScript有两个重要的对象。客户端全局对象处于作用域链的顶级，并且是全局变量和函数所定义的地方。事实上，全局对象会在窗口或窗体载入新内容时被替换。我们称为 **Window对象** 的对象实际上不是全局对象，而是全局对象的一个代理。

+   函数在定义它的作用域中执行，而不是调用他的作用域中执行。

    +   要记住构造函数也是函数，所以当用构造函数和相关的原型对象定义一个类时，那个类只在一个单独的窗口中定义。即不同window中的构造函数，不相等
    +   和用户定义的类不同，内置的类（比如String，Date和RegExp）都会在所有的窗口中自动预定义。但是要注意，每个窗口都有构造函数的一个独立副本和构造函数对应原型对象的一个独立副本。例如，每个窗口都有自己的String（）构造函数和String.prototype对象的副本。因此，如果编写一个操作JavaScript字符串的新方法，并且通过它赋值给当前窗口中的String.prototype对象而使他成为String类的一个方法，那么该窗口中的所有字符串都可以使用这个新方法。但是，别的窗口定义的字符串不能使用这个新方法。
    +   事实上，每个Window都有自己的原型对象，这意味着instance不能跨窗口工作。例如，当用instanceof来比较窗口A的一个字符串和窗口B的String()构造函数时，结果会为false。

+    jquery的回调函数执行顺序，success版本与then版本，success优先于then

+   在由window.open()方法创建的窗口中，opener属性引用的是打开它的脚本的window对象。在其他窗口中，opener为null

    +   **注意，要显式的使用标识符window，这样可以避免混淆Window对象的close方法和Document对象的close方法。如果正在从事件处理程序调用close(), 这很重要**

+   如果在HTML文档中用id属性来为元素命名，并且如果Window对象没有此名字的属性，Window对象会赋予一个属性，他的名字是id属性的值，而他们的值指向表示文档元素的HTMLElement对象。

    +   这也是为什么规范要求id不能用数字开头，因为js变量名不能数字开头？

    +   如果id是数字打头，则用window['id值']的方式访问到对应的HTMLElement对象

    +   重要的警告，如果Window对象已经具备此名字的属性，这就不会发生

    +   <span style="color:red;font-weight:bold">元素ID作为全局变量的隐式应用是Web浏览器演化过程中遗留的怪癖。主要是出于与已有Web页面后向兼容性的考虑。但这里并不推荐使用这种做法，浏览器厂商可以在任何时候为Window对象定义新属性，而这些新属性都会破坏使用了此属性名的隐式定义的代码。反之，用document.getElementById来显式查找元素。</span>

    +   以下元素，可以通过name属性达到跟id一样的效果，如果存在多个相同name属性的元素，则封装成数组形式

        +   iframe， embed，form，object

        +   **有name或id属性的iframe元素是个特殊的例子，为他们隐式创建的变量不会引用表示元素自身的Element对象，而是引用表示iframe元素创建的嵌套浏览器窗体的Window对象**

        +   object是什么元素？

            +   表示引入一个外部资源，可能是一张图片，一个嵌入的浏览上下文，或者是插件资源

                ```html 
                <object type='application/pdf' data='./HTML与CSS入门经典(第8版).pdf' height='200' width='259'></object>
                ```

                

+   同源策略

    +   作用	

        +   当Web页面使用多个<iframe>元素或者打开其他浏览器窗口。脚本只能读取和所属文档来源相同的窗口和文档的属性
        +   使用XMLHttpRequest生成的HTTP请求，这个对象允许客户端Javascript生成任意的HTTP请求到脚本所属文档的Web服务器，但是不允许脚本和其他Web服务器通信

    +   如果没有这一限制，恶意脚本（通过防火墙载入到安全的公司内网的浏览器中）可能会打开一个空的窗口，欺骗用户进入并使用这个窗口在内网上浏览文件。恶意脚本就能够读取窗口的内容并将其发送回自己的服务器。

    +   不严格的同源策略

        +   为了支持这种类型的多域名站点，可以使用Document对象的domain属性。

        +   跨域资源共享 (**Cross-Origin Resource Sharing**)  。使用**Origin**请求头和**Access-Control-Allow-Origin**响应头来扩展HTTP。它允许服务器用头信息显式的列出源，或使用通配符来匹配所有的源并允许由任何地址请求文件。

        +   跨文档消息 (**cross-document messaging**) ，允许来自一个文件的脚本可以传递文本消息到另一个文档里的脚本，而不管脚本的来源是否不同。调用Window对象上的**postMessage()**方法，可以异步传递消息事件（**可以用onmessage事件句柄处理程序函数来处理它**）到窗口的文档里。**一个文档里的脚本还是不能调用在其他文档里的方法和读取属性，但他们可以用这种消息传递技术来实现安全的通信**

            

+   JavaScript针对恶意代码的防线是在自己支持的某些功能上施加限制。比如

    +   Javascript可以打开一个新的浏览器窗口，但是为了防止广告商滥用弹出窗口，很多浏览器限制了这一功能，使得只有为了响应鼠标单击这样的用户触发事件的时候，才能使用它。
    +   Javascript程序可以关闭自己打开的浏览器窗口，但是不允许它不经过用户确认就关闭其他的窗口
    +   HTML FileUpload元素的value属性是只读的。如果可以设置这个属性，脚本就能设置它为任意期望的文件名，从而导致表单上传指定文件(比如密码文件)的内容到服务器
    +   脚本不能读取从不同服务器载入的文档的内容，除非这个就是包含该脚本的文档。类似的，一个脚本不能在来自不同服务器的文档上注册事件监听器。这就防止脚本窃取其他页面的用户输入。这一限制叫做 **同源策略**

+   条件注释是什么？

    +   JavaScript条件注释

    ```javascript
    @if(@_jscript)。JScript
    是Microsoft自己的JavaScript解释器的名字，而@_jscript变量在IE中总是为true
    
    /*@cc_on
    @if(@_jscript)//这里的代码在一条条件注释中，也在一条常规的JavaScript注释中
    //IE会执行这段代码，其他浏览器不执行它
    alert('You are using Internet Explorer);
    @else*///这段代码并没在JavaScript注释中，但仍然在IE条件注释中
    //也就是说除了IE之外的所有浏览器都执行这里的代码
    alert('You are not using Internet Explorer');/*@end
    @*/
    ```

    

    +   HTML条件注释

    ```html
    
    
    ＜!--[if IE 6]＞
    This content is actually inside an HTML comment.
    It will only be displayed in IE 6.
    ＜![endif]--＞
    ＜!--[if lte IE 7]＞
    This content will only be displayed by IE 5,6 and 7 and earlier.
    lte stands for"less than or equal".You can also use"lt","gt"and"gte".
    ＜![endif]--＞
    ＜!--[if!IE]＞＜--＞
    This is normal HTML content,but IE will not display it
    because of the comment above and the comment below.
    ＜!--＞＜![endif]--＞
    This is normal content,displayed by all browsers.
    
    
    ```

    

+   

## ES6基础

+   当使用ES6的默认参数时，arguments对象的表现和ES5的严格模式一致，不管函数是否显式设定为严格模式。默认参数的存在会使arguments对象对该命名参数解绑。

+   如果浏览器原生支持Symbol，那么使用Reflect.ownKeys读取出inject的所有key；如果浏览器原生不支持Symbol，那么使用Object.keys读取key。由于通过Reflect.ownKeys读出的key包括不可枚举的属性，所以代码中需要使用filter将不可枚举的属性过滤掉。

+ method赋值属于值赋值类型，而非引用赋值类型

    ```javascript
    var test = ()=>console.log(12);
    var hui = test;
    test = ()=>console.log(22);
    hui !== test // true
    ```

+ `Object.assign`函数，会用`source`对象的属性值替换`target`对象的同名属性。而`lodash`的`defaultsDeep`函数不会

  ```javascript
  var test={}
  var obj = {value:new Date()}
  lodash.defaultsDeep(test, obj) // 此时由于是深拷贝，obj改变值不会影响test
  Object.assign(test, obj) // 此时由于是浅拷贝，obj改变值会影响test，因为assign会把source的值覆盖test的值
  lodash.defaultsDeep(test, obj) // 此时依然是浅拷贝，因此defaultsDeep不会覆盖test的属性
  ```

+ `Object.getOwnPropertyDescriptor`方法可以获取该属性的描述对象。描述对象的`enumerable`属性，称为`可枚举性`，如果为`false`，以下四个操作会忽略该属性

    + `for...in` **ES5，仅该方法能返回继承的属性** 
    + `Object.keys()` **ES5**
    + `JSON.stringify()` **ES5**
    + `Object.assign()` **ES6**

    ```javascript
    let obj = {foo:123}
    Object.getOwnPropertyDescriptor(obj, 'foo')
    ```

    >   实际上，引入`enumerable`的最初目的，就是让某些属性可以规避掉`for...in`操作。比如，对象原型的`toString`方法，以及数组的`length`属性，就通过这种手段，不会被`for...in`遍历到。另外，ES6规定，所有Class的原型方法都是不可枚举的。

    >   总的来说，操作中引入继承的属性会让问题复杂化，大多数时候，我们只关心对象自身的属性。所以，尽量不要用`for...in`循环，而用`Object.keys()`代替

+ 属性遍历的次序规则

    + 所有属性名为 **数值** 的属性，按照 **数字** 排序
    + 所有属性名为 **字符串** 的属性，按照 **生成时间** 排序
    + 所有属性名为 **Symbol** 的属性，按照 **生成时间** 排序

    ```javascript
    Reflect.ownKeys({[Symbol()]:0, b:0, 10:0, 2:0, a:0})
    // ['2', '10', 'b', 'a', Symbol()]
    ```

+ `__proto__`属性最好不要直接操作，而是使用如下方法

    + `Object.setPrototypeOf` 写操作
    + `Object.getPrototypeOf` 读操作
    + `Object.create` 生成操作

+ `Generator`函数中，`next`方法可以带一个参数，该参数就会被当作上一个``yield`语句的返回值。

## CSS

+   当border-radius被子元素的方角覆盖，让border-radius搭配overflow：auto使用，味道更佳

## Vue

+   Vue在2.5.X版本，会把html属性方式注册的事件名用小写保存，Vue在2.6.X版本，则保留原来的大小写的事件名字。
    
+    Vue中的props大小写自动识别，event名不自动识别。但是前提是，在字符串模板下，没有这种限制，也就是大小写可以敏感。所谓字符串模板，包括new Vue的template选项，也包括单文件Vue组件的template标签，会经过vue-loader编译。

+   Vue-Router对象会存储query的参数值，因此能保存数据类型。当reload整个页面时，Router对象丢失，数据类型数据丢失，所有参数都解析成字符串类型。
    
+   Vue的样式Scoped，注意，不会覆盖，但是有继承规则，子组件会继承父组件相同类名的css属性。即子组件存在就拿子组件，不存在就拿父组件。
    
+   >   注意 `mounted` **不会**保证所有的子组件也都一起被挂载。如果你希望等到整个视图都渲染完毕，可以在 `mounted` 内部使用 vm.$nextTick
    >
    >   《Vue官方网站》
    
+   Vue不能使用Symbol用作data的首层key值

+   在模板中使用 **v-on** 注册在子组件标签上的事件；如果是平台标签（例如div），则创建元素并插入到DOM中，同时会将标签上使用 **v-on** 注册的事件注册到浏览器事件中。

+   所有使用 **vm.$on** 注册的事件监听器都会保存到 **vm.\_events** 属性中，父组件向自己注册的事件，这些事件最终会被保存在 **vm.$options._parentListeners** 中

+   <span style="color:green">Vue的 ** $set** 在添加了响应式效果时，是立即触发的watcher的吗？</span>

+   Vue给一个对象添加响应式，即是重写其属性的getter/setter，如果对象本身被重新赋值，则重写的失效，且对象本身是没有重写getter/setter这一说。所以数组中

    ```vue
    // 无法触发响应式
    this.test[0] = {name:''}
    
    // 能触发响应式，重写了push函数，使添加后的对象，有重写的getter/setter
    this.test.push({name:''})
    
    // 这样修改是能触发响应式的，因为属性是重写了getter/setter的
    this.test[0].name = ''
    
    // 这样是不能触发响应式的，同时，响应式的对象被覆盖，后续调用this.test[0].name是没有响应式的
    this.test[0] = {name:''}
    
    // TODO 有问题，如果赋值语句
    ```

    

+   Vue父子组件生命周期执行顺序

    +   父组件 `created` -> `beforeMount`
        +   所有子组件 `created` -> `beforeMount`
        +   所有子组件 `mounted` （**所有子组件beforeMount执行完成后，再依次执行mounted**）
    +   父组件 `mounted`
    +   父组件 `beforeDestroy`
        +   所有子组件 `beforeDestroy` -> `destroyed` （**不会出现所有子组件都beforeDestroyed后，再一起destroyed**）
    +   父组件 `destroyed`
    +   update周期，子组件触发update，不会顺带父组件也触发updated

+   Vue组件生命周期

    +   执行  **initLifecycle** 函数`
        +   把自己push到父节点的 **$children数组**
        +   设置 **$parent** 为父节点，初始化 **$children** 数组
        +   初始化 **$refs** 对象
    +   执行 **initEvents** 函数
        +   更新 **$listeners** 对象，这里是父组件附加的事件
    +   执行 **initRender** 函数
        +   处理 **$slots**
        +   创建响应变量 **$attrs**, **$listeners**
    +   <span style="color:red">执行 **beforeCreate** 钩子</span>
    +   执行 **initInjections** 函数
        +   设置 **inject** 变量 (<span style="color:red">不是响应式变量</span>)
    +   执行 **initState** 函数
        +   执行 **initProps** 函数
            +   创建props的响应变量
        +   执行 **initMethods** 函数
            +   遍历**methods**的**Function类型**属性，通过**bind**将当前组件**vm**绑定到method上下文
        +   执行 **initData** 函数
            +   将**data**里面的属性添加观察
        +   执行 **initComputed** 函数 
        +   执行 **initWatch** 函数
    +   执行**initProvide**函数
    +   <span style="color:red">执行 **created** 钩子</span> 
    +   调用 **$mount** 函数
        +   将 **template** 编译为 **render** 函数

+   Vue响应系统流程

    

+   挂载完毕后，Vue.js 处于已挂载阶段。已挂载阶段会持续追踪状态的变化，当数据（状态）发生变化时，Watcher会通知虚拟DOM重新渲染视图。

    +   **能否解释，watch必须在mounted及之后周期才能触发？**

+   `createElement` 函数参数说明

    ```javascript
    // @returns {VNode}
    function createElement(
        // {String | Object | Function}
        // 一个HTML标签名、组件选项对象，或者resolve了上述任何一种的一个async函数。必填项
        'div',
        
        // {Object}
        // 一个与模板中attribute对应的数据对象。可选。
        {
            attrs:{
                name: 'attrName'
            },
            ...
        },
        
        // {String | Array}
        // 子级虚拟节点 (VNodes)，由`createElement()`构建而成，也可以使用字符串来生成‘文本虚拟节点’。可选。
            
        [
            '先写一些文字',
            createElement('h1', '一则头条'),
            createElement(MyComponent, {
                props:{
                    someProp: 'foobar'
                }
            })
        ]
    )
    ```

+   `render` 函数参数说明

    ```javascript
    function render(
        
    createElement,
        
    context
    )
    ```

+   `directive` 新增指令函数参数说明

    ```javascript
    Vue.directive('focus', (el, binding, vnode) => {
        let origin = el.outerHTML;
        el.outerHTML = `
    		<div>
    			<p>new Content</p>
    			${origin}
    		</div>
    `;
    })
    ```

+   自定义指令、自定义组件都可以另外新建js文件来创建，然后main.js引入即可

+   deep是否有用？如果属性是data里面的，难道不会自动触发数据变动监控，毕竟有重写getset。如果属性是另外新增的，为什么会触发数据变动监控，又没有重写getset。 待验证结论：deep仅对重写了getset的属性值变化有效，如果是变动整个对象，则不需要deep，仅在变动某个属性时，deep生效，且deep为false时，watch不生效。

    +   $set 添加属性，属于对象变动，而非属性值变动
    +   deep的作用，仅仅是遍历该对象的所有属性，以触发其getter函数，使其被添加到watcher依赖。因此，deep如果要生效，必须是重写后的getset。 -- 源码

+   [x] 两次$set 引发一次数据变动监控时，是第一次引发，还是第二次引发？如果第一次引发，第二次就完全不引发了吗？

    本轮执行前，接收到的所有set，当作一次变动，统一执行set后的结果进行数据变动检测。如果set放到下一轮执行，则分成两次数据变动检测。

+   [ ] props默认父组件单向下发更新给子组件，但是如果子组件是需要处理props数据的话，需要注意，除非是computed之类的计算，否则需要this.$watch之后做处理，才能实时获取父组件数据更新

+   keepalive用在包含了路由的组件中，则仅该组件被keepalive，路由内包含的组件不受影响

+   vue的watch中，deep依赖于响应式数据，如属性未设置响应式，则无法触发deep效果。deep仅作用于对象的属性值的变化，而不作用于对象的属性增减

+   vue的\$set方法仅适用于第一次为该对象添加属性，如果已经存在属性，则 \$set方法添加响应式的效果失效。已经存在该属性时，set方法直接赋值，并返回该值

+   数据监测时，如果是同一个watcher，则watch.id相同，这导致除非上一次watcher被触发执行函数，否则下一次监听不会生效，直接跳过

    >   Vue用一个 `queue` 收集依赖的执行，在下次微任务执行的时候统一执行 `queue` 中的 `Watcher` 的 `run` 操作，与此同时，相同 `id` 的 `watcher` 不会重复添加到 `queue` 中，因此也不会重复执行多次的视图渲染
    >
    >   --《深入浅出Vue源码》

+	Vue模板可访问的白名单全局变量
	+ 'Infinity,undefined,NaN,isFinite,isNaN,' 
    'parseFloat,parseInt,decodeURI,decodeURIComponent,encodeURI,encodeURIComponent,' 
    'Math,Number,Date,Array,Object,Boolean,String,RegExp,Map,Set,JSON,Intl,' 
    'require' 

+   事件循环机制

    +   宏任务 `setTimeout`, `setInterval`, `setImmediate`, `Script脚本`, `I/O操作`, `UI渲染`
    +   微任务 `pomise`, `process.nextTick`, `MutationObserver`
    +   事件循环流程
        +   宏队列空，宏队列只有script脚本，执行期间产生的 宏任务、微任务 推送到对应的队列
        +   执行全部宏任务里面的微任务
        +   执行DOM操作，渲染更新页面
        +   执行 Web worker等相关任务
        +   循环，取出宏队列中的一个宏事件执行



## Vue 待验证

+   vue的props的对象类型，默认值使用es6风格定义函数，则取到undefined，使用es5风格函数，则能正常取到{}对象。(使用es5风格获取的对象，是ob的)
+   父子组件如果同时使用了scoped的style，则子组件的直接使用了父组件的style，？？？

## 编程风格

+   作用域

    +   优先使用 `const` > `let` > `var` 
    +   所有的函数都应该设置为**常量**

+   字符串

    +   静态字符串一律使用 **单引号**，动态字符串使用 **反引号**

+   解构赋值

    +   使用 **数组成员** 对变量赋值时，优先使用 **解构赋值**
    +   函数的参数如果是 **对象** 的成员，优先使用 **解构赋值**
    +   如果函数返回多个值，优先使用 **对象** 的解构赋值，而不是 **数组** 的解构赋值。这样 **便于以后添加返回值，以及更改返回值的顺序**

    ```javascript
    function processInput(input) {
        return {left, right, top, bottom}
    }
    
    function getFullName({firstName, lastName}) {
        
    }
    
    function getFullName(obj) {
        const {firstName, lastName} = obj;
    }
    ```

+   对象

    +   **单行** 定义的对象，**最后一个成员不以逗号结尾**
    +   **多行** 定义的对象，**最后一个成员以逗号结尾**
    +   **对象尽量静态化**，一旦定义，**就不得随意添加新的属性**。如果添加属性不可避免，**要使用Object.assign方法**

    ```javascript
    const a = {k1:v1, k2:v2};
    const b = {
        k1:v1,
        k2:v2,
    };
    
    const a = {x:null};
    a.x = 3;
    
    const a = {};
    Object.assign(a, {x:3});
    ```

    +   对象的属性和方法，**尽量使用简洁表达法**

    ```javascript
    var ref = 'some value';
    
    const atom = {
        ref, 
        value:1,
        addValue(value) {
            return atom.value+value;
        }
    }
    ```

+   数组

    +   使用 **扩展运算符(...)** 拷贝数组
    +   使用 **Array.from方法** 将类似数组的对象转为数组

    ```javascript
    const itemsCopy = [...items];
    const nodes = Array.from(document.querySelectorAll('.foo'));
    ```

+   函数

    +   尽量使用 **箭头函数** ，这样更简洁，而且绑定了 **this**
    +   所有 **配置项** 都应该集中在一个对象，**放在最后一个参数**，布尔值不可以直接作为参数
    +   不要在函数体内使用 **arguments** 变量，使用 **rest运算符(...)代替**。因为rest运算符显式表明你想要获取参数，而且arguments是一个类似数组的对象，而rest运算符可以提供一个真正的数组。
    +   使用 **默认值** 语法设置函数参数的默认值

    ```javascript
    function divide(a, b, {option = false} = {}) {
        
    }
    ```

+   Map结构

    +   只有模拟现实世界的实体对象时，才使用 **Object**，如果只是需要 **key:value** 的数据结构，使用Map结构。因为Map有内建的遍历机制。

+   Class

    +   使用Class，取代需要 **prototype** 的操作，因为Class的写法更简洁，更易于理解。
    +   使用 **extends** 实现继承，因为这样更简单，不会有破坏 **instanceof** 运算的危险

+   模块

    +   使用 **import** 取代 **require**
    +   使用 **export** 取代 **module.exports**
    +   如果模块只有一个输出值，使用 **export default**
    +   如果模块 **默认输出** 一个 **函数**，函数名的首字母应该 **小写**。**默认输出** 一个 **对象**，对象名的首字母应该 **大写**

    ```javascript
    export default function makeStyleGuide() {
        
    }
    
    export default const StyleGuide = {
        es6:{
            
        }
    }
    ```

+   ESLint

    +   使用 **Airbnb** 语法规则

    ```shell
    npm i -g eslint
    npm i -g eslint-config-airbnb
    ```

    +   新建 **.eslintrc**

    ```json
    {
        "extends":"eslint-config-airbnb"
    }
    ```

    +   使用 **ESLint** 检查文件代码

    ```sh
    eslint index.js
    ```

    
