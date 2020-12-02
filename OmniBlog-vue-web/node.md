

this.$ruter.push()
解释：这个方法会向history添加一个记录，点击后退会返回到上一个页面
使用方法：

//字符串
this.$ruter.push(’home');
//对象
this.$ruter.push({path:’home'});
//命名的路由
this.$ruter.push({name:'user'});
//带参数 /regisetr?role=user
this.$ruter.push({path:'register',query:{role:'user'}});
// 跳转后的页面获取参数
 this.role = this.$route.query.role;


this.$ruter.replace()
解释：这个方法不会向history添加一个记录，点击后退不会返回到上一个页面，点击返回，会跳转到上上一个页面。上一个记录是不存在的。

this.$ruter.go(n)
解释：n表示当前页面向后或向前跳转多少个页面，n可以为负数，正数表示跳转到上一个页面。
使用方法：

this.$ruter.go(-1)；//后退一步相当于history.back();
this.$ruter.go(1)；//前进一步，相当于 history.forward();
this.$ruter.go(100)；//history记录不够则跳转失败``
