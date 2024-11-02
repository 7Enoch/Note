"ui";
$ui.layout( 
    <horizontal margin="30">
        <button id="ks" w="auto" text="快手极速版"/>
        <button id="dy" w="auto" text="抖音极速版"/>
        <button id="mt" w="auto" text="美团"/>
    </horizontal>
);
//点击快手操作
ui.ks.click(()=>{
    toast(ui.ks.getText()+"被点击")
    jump(ui.ks.getText())
})
//点击抖音操作
ui.dy.click(()=>{
    toast(ui.dy.getText()+"被点击")
    jump(ui.dy.getText())

})
//点击美团操作
ui.mt.click(()=>{
    toast(ui.mt.getText()+"被点击")
    jump(ui.mt.getText())

})

//跳转到选择时间页面 
/**
 * 
 * @param {选择的软件名称} name 
 */
let jump = (name) => {
    ui.layout(
        <vertical gravity="center" margin="30">
            <text  gravity="center"  id="name"></text>
            <input id="time" w="auto" maxLength="3"  singleLine="true" inputType="number" hint="请输入运行时间" />
            <button id="submit" w="auto">确定</button>
        </vertical>
    );
    ui.name.setText(name)
   
    //点击确认按钮
    ui.submit.click(()=>{
       
        //获取运行时间
        let time = ui.time.getText()
        //如果没有选择
        if(time==''){
            toast("时间不能为空")
            return
        }
        text(time)
        //开始运行
        main(name,time)
        
    })
}

//定义函数
 function main(name,time) {
    let bagName = app.getPackageName(name)
    if (!bagName) {
        toast("未安装" + name)
    }
    //启动需要运行的软件
    app.launch(bagName)
    if(name=="快手极速版"){
        //点击侧边栏
       var left_btn = id("left_btn").findOne()
       left_btn.click()
        //如果有控件遮挡 就先点击取消
        
    }else if(name == "抖音极速版"){
        
    }else if(name == "美团"){

    }

}

//

//滑动函数
function slide(time){
    //7秒一次滑动
    var slide = setInterval(function () {
        //text("滑动");
        swipe(600, 2000, 600, 1000, 300)
    }, 7000);

    //time分钟后取消循环
    setTimeout(function () {
        clearInterval(slide);
    }, time * 60000);
}
