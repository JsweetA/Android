// var result = JavaScriptInterface.jsontohtml();
// var obj = eval("(" + result + ")"); //解析json字符串
// function showAndroidToast(toast) {
//   JavaScriptInterface.showToast(toast);
// }
var title = document.getElementById('title');
var title2 = document.getElementById('title2');
var time = document.getElementById('time');
var resource = document.getElementById('resource');
var article = document.getElementById('article');
var editor = document.getElementById('editor');
var player = document.getElementById('player');
getjsonData();

function getjsonData() {
//  var result = JavaScriptInterface.jsontohtml();
//  var obj = eval("("+result+")");//解析json字符串
  var news = {
    title: '中国中央委员会第六次全体会议公报',
    title2: '中国共产党第十九届中央委员会第六次全体会议公报（2021年11月11日中国共产党第十九届中央委员会第六次全体会议通过）',
    time: '2021 年 11 月 12 日',
    resource: '“学习强国”学习平台',
    article: '中国共产党第十九届中央委员会第六次全体会议，于2021年11月8日至11日在北京举行。\n' +
      '出席这次全会的有，中央委员197人，候补中央委员151人。中央纪律检查委员会常务委员会委员和有关方面负责同志列席会议。\n' +
      '全会由中央政治局主持。中央委员会总书记习近平作了重要讲话。\n' +
      '全会听取和讨论了习近平受中央政治局委托作的工作报告，审议通过了《中共中央关于党的百年奋斗重大成就和历史经验的决议》，审议通过了《关于召开党的第二十次全国代表大会的决议》。习近平就《中共中央关于党的百年奋斗重大成就和历史经验的决议（讨论稿）》向全会作了说明。\n' +
      '全会认为，总结党的百年奋斗重大成就和历史经验，是在建党百年历史条件下开启全面建设社会主义现代化国家新征程、在新时代坚持和发展中国特色社会主义的需要；是增强政治意识、大局意识、核心意识、看齐意识，坚定道路自信、理论自信、制度自信、文化自信，做到坚决维护习近平同志党中央的核心、全党的核心地位，坚决维护党中央权威和集中统一领导，确保全党步调一致向前进的需要；是推进党的自我革命、提高全党斗争本领和应对风险挑战能力、永葆党的生机活力、团结带领全国各族人民为实现中华民族伟大复兴的中国梦而继续奋斗的需要。全党要坚持唯物史观和正确党史观，从党的百年奋斗中看清楚过去我们为什么能够成功、弄明白未来我们怎样才能继续成功，从而更加坚定、更加自觉地践行初心使命，在新时代更好坚持和发展中国特色社会主义。',
    editor: '秦辰宇',
  }
  showData(news);
}

function showData(news) {
  console.log(news)
  title.innerHTML = news.title;
  title2.innerHTML = news.title2;
  time.innerHTML = news.time;
  resource.innerHTML = `来源：${news.resource}`;
  //按照换行符分隔成数组
  const tempArr = news.article.split(/[(\r\n)\r\n]+/);
  //删除空项
  tempArr.forEach((item, index) => {
    if (!item) {
      snsArr.splice(index, 1);
    }
  });
  //循环添加单个段落
  tempArr.forEach((item, index) => {
    var p = document.createElement('div');
    console.log(item)
    p.id = `p${index}`;
    p.innerText = item;
    article.appendChild(p);
  });
  editor.innerHTML = `责任编辑：${news.editor}`;
}

function changeBroadIcon(flag) {
  if (flag) {
    var play = document.getElementById('play');
    play.src = './images/onplay.png';
    play.onclick = () => {
      stop();
    }
  } else {
    var play = document.getElementById('play');
    play.src = './images/broad.png';
    play.onclick = () => {
      fun();
    }
  }
}

function fun() {
  const values = title.innerText + title2.innerText + time.innerText + article.innerText + editor.innerText;
  console.log(values);
  var url = "http://tts.baidu.com/text2audio?lan=zh&ie=UTF-8&text=" + encodeURI(values);
  var div = document.createElement('div');
  div.innerHTML = `<audio controls id="audio"><source src="${url}" type="audio/mpeg"></audio>`
  player.appendChild(div);
  var audio = document.getElementById('audio');
  audio.play();
  changeBroadIcon(true);
}

function stop() {
  var audio = document.getElementById('audio');
  //停止音频
  audio.currentTime = 0;
  audio.pause();
  //删除dom
  player.removeChild(player.children[0]);
  changeBroadIcon(false);
}