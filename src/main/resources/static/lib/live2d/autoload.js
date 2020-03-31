//初始化看板娘，会自动加载指定目录下的waifu-tips.json
$(window).on("load", function () {
    initWidget("https://cdn.jsdelivr.net/gh/stevenjoezhang/live2d-widget/waifu-tips.json", "https://live2d.fghrsh.net/api");
});
//initWidget第一个参数为waifu-tips.json的路径
//第二个参数为api地址（无需修改）
//api后端可自行搭建，参考https://github.com/fghrsh/live2d_api

