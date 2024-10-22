export signhost='3.xjyyds.cf:18848'

# 太平通
# 自己捉包域名 ecustomer.cntaiping.com 的两个值:
#  x-ac-token-ticket (或tokenkey)
#  x-ac-device-id (可选)
# 把这两个用#连起来填到变量 tptCookie 里, 多账号换行或&或@隔开
# 不填x-ac-device-id的话脚本每次运行会随机生成一个#2a9604d35395679f
export tptCookie='eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOltdLCJzdWIiOiIxNzI5MDY5MjE3NzcyQm1TTVY4MmczZ0ZsQVE4NktieFR2aCIsInVzZXJfbmFtZSI6IjY1MzI2MjY3ODk2MTI5OTQ1NjAiLCJsb2dpY19pZCI6NDIyMDYxOTM0MjMwNDM3ODg4LCJpc3MiOiJraHQudGFpcGluZyIsInNjb3BlIjpbXSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwianRpIjoiMTcyOTA2OTUyMTMyMGZSVHR5VE82WEpyNWF3MENpUDJDZSIsImFkZGl0aW9uYWxJbmZvIjp7fSwiaWF0IjoxNzI5MDY5NTIxLCJleHAiOjE3OTM4Njk1MjEsImF1dGhvcml0aWVzIjpbXSwiY2xpZW50X2lkIjoiVFBUX0FQUCIsInJvbGVfdHlwZXMiOiJDIn0.ShsUbkgcVKw2nheDAdcJ7U-49JLh8hdrzwOJllZ_WqR8DNqno4kv_ePGtvOpGOvwofwzHhH5nQ9XcdbgxmX69i2bG_FTfTrpHr7TZXM_yvrgsFD4bB0lxTrjqW6BnoHuv0YNYJ0md_1m6sfcYIN1QDnFQIEj40yMAgdDssoKRQo
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOltdLCJzdWIiOiIxNzI5MTcyNzg4NzU5QjZXalBjMXNTaGFvUGZGM3k3NzdLbiIsInVzZXJfbmFtZSI6IjgwODUyMzM1MTA2OTExODQ2NCIsImxvZ2ljX2lkIjo0MjI4NzI3Nzg1OTQwMTMxODUsImlzcyI6ImtodC50YWlwaW5nIiwic2NvcGUiOltdLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJqdGkiOiIxNzI5MTcyODMyMDg4QmZJR1daRFgwRjliSElGWjkyWElpVyIsImFkZGl0aW9uYWxJbmZvIjp7fSwiaWF0IjoxNzI5MTcyODMyLCJleHAiOjE3OTM5NzI4MzIsImF1dGhvcml0aWVzIjpbXSwiY2xpZW50X2lkIjoiVFBUX0FQUCJ9.iHjFNd2culzU92Ku6F5eOInjTSaRPHlZZNdpd21gqWIqF8yIjuqd4aN_RxSj9-ZLzho5_jpO5z-shNIqn86DNomm81NDQ9GEqoOOWGi4YhLR-E8ORbnDcZGoFUIlbmk2N1bXuFRXPq9se4Ol6I3eNuz_EySUUlKhHtEp3D2kkgo
'
export TaiPingTong='[
    {
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOltdLCJzdWIiOiIxNzI5MDY5MjE3NzcyQm1TTVY4MmczZ0ZsQVE4NktieFR2aCIsInVzZXJfbmFtZSI6IjY1MzI2MjY3ODk2MTI5OTQ1NjAiLCJsb2dpY19pZCI6NDIyMDYxOTM0MjMwNDM3ODg4LCJpc3MiOiJraHQudGFpcGluZyIsInNjb3BlIjpbXSwiZ3JhbnRfdHlwZSI6InBhc3N3b3JkIiwianRpIjoiMTcyOTQ5NjkwODgwNEN0aEx2ZFJ0b0hpeW5MbjduYXYwY04iLCJhZGRpdGlvbmFsSW5mbyI6e30sImlhdCI6MTcyOTQ5NjkwOCwiZXhwIjoxNzk0Mjk2OTA4LCJhdXRob3JpdGllcyI6W10sImNsaWVudF9pZCI6IlRQVF9BUFAiLCJyb2xlX3R5cGVzIjoiQyJ9.JRuxH2FKT_CYMiu87o3vHiXDMUy701eZjGLt1LdqERSUr1f4ECxMiDHiDQzy1LlfGdSV5EJDJWXB7q7e-XlI1iGfT9EzAoEEva1pVgi6zNO4znfg67cSNBKJESq9R7LoxpLAylFqEmflGeNlcISbMnrCtQq2HFnOv5QlLFlgsYU",
        "userId": "0730"
    },{
		"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOltdLCJzdWIiOiIxNzI5MTcyNzg4NzU5QjZXalBjMXNTaGFvUGZGM3k3NzdLbiIsInVzZXJfbmFtZSI6IjgwODUyMzM1MTA2OTExODQ2NCIsImxvZ2ljX2lkIjo0MjI4NzI3Nzg1OTQwMTMxODUsImlzcyI6ImtodC50YWlwaW5nIiwic2NvcGUiOltdLCJncmFudF90eXBlIjoicGFzc3dvcmQiLCJqdGkiOiIxNzI5MTcyODMyMDg4QmZJR1daRFgwRjliSElGWjkyWElpVyIsImFkZGl0aW9uYWxJbmZvIjp7fSwiaWF0IjoxNzI5MTcyODMyLCJleHAiOjE3OTM5NzI4MzIsImF1dGhvcml0aWVzIjpbXSwiY2xpZW50X2lkIjoiVFBUX0FQUCJ9.iHjFNd2culzU92Ku6F5eOInjTSaRPHlZZNdpd21gqWIqF8yIjuqd4aN_RxSj9-ZLzho5_jpO5z-shNIqn86DNomm81NDQ9GEqoOOWGi4YhLR-E8ORbnDcZGoFUIlbmk2N1bXuFRXPq9se4Ol6I3eNuz_EySUUlKhHtEp3D2kkgo",
		"userId": "5833"
	}
]'
#     益禾堂
#   * 抓取webapi.qmai.cn域名下Qm-User-Token的值填入 变量名:yhtck ck
#   * 如需用户相互助力 抓取https://logger.qmai.cn/report.do请求体中的uid填入(也可以直接搜索uid) yhtck uid
export yhtck='[
	{
		"ck": "AhAZPXrSnV0gb54nuKhz-yrEDpKHtR7_lquhgm6T3jnO_lAN-7w1uIbzc81-_eCX",
		"uid": "1053229310156075009",
        "user": "5833"
	},
	{
		"ck": "R5zV4SptsiMKnUdr8XFd4-1Frks3wu4NCImVhfbY_R8nPHCXkbfBOaLZORXNEG2I",
		"uid": "1053004234940854272",
        "user": "0730"
	},
	{
		"ck": "ZZkOESLBi0V3op_tkLMZMwVfm3NH7FwmRltcBlta6ik0e-QnVKLTFsYInGHc4roH",
		"uid": "1053234220058619904",
        "user": "8104"
	},
	{
		"ck": "Lr_1-hQHU1imMidynSkl9zeNPKAI_gbBfRtvCSYNax41BcpstOcwx0zUmJwIckv2",
		"uid": "1052951964249411584",
        "user": "5344"
	}
]'
## 阅读
## 通用
## UA
export yuanshen_useragent='Mozilla/5.0 (Linux; Android 9; ELE-AL00 Build/HUAWEIELE-AL0001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/67.0.3396.87 XWEB/1170 MMWEBSDK/191201 Mobile Safari/537.36 MMWEBID/873 MicroMessenger/7.0.10.1580(0x27000AFE) Process/tools NetType/4G Language/zh_CN ABI/arm64'
export yuanshen_apptoken='AT_Z5U3t2kky7uDc7Ss81qat0cyDqzsNDSj'
export yuanshen_topicid='81422'
## 点点赚阅读
## 变量名：s_ddz    变量名值的格式：备注#cookie#提现金额（元）#wxpusher的apptoken#主题id#提现支付宝名字#支付宝账号
export s_ddz='1#user_openid=oiWmR6blhGUCvcVybrqn2AyEz5Vg; uid=144112; PHPSESSID=e8fd983ed5c356370487cf6a5c267d2e#1#AT_Z5U3t2kky7uDc7Ss81qat0cyDqzsNDSj#34236#低调D树苗#15165150730'
## 小阅阅
export yuanshen_xyy='bd0784d1fd442aef0d39e1a28f751e5b#oZdBp07eZsdi6AfNIZELRP8E3dZE
fa003efab9d2fbc4b0876bcf6d183433#oZdBp08tjitz2Y51HGYYUGqm1Sqc
b1fcd18db0f4e16904db543c46b4e470#oZdBp0_0uthcZ_XciIwTrw66BDjw
c4af0d622b74118cd15e1f89662e3814#oZdBp0_DAQ0_o2kRdtDJvtOQRKq4'

## 探子报
## 抓取tzapi.tanzibao.com域名下user-token的值填入
## 变量名:yuanshen_tzb=63b8ae260ab36fdb5353a0ff035492ab
export yuanshen_tzb="28875adbb3f3a17b9f9f7f914393c134
28875adbb3f3a17b9f9f7f914393c134
881321a8b37139b5e5500d9c6f1b25f6
4cd173c6ef3233480856685bec0dca5f"

# 15 8 * * * pddFruit.js, tag=拼多多果园, enabled=true
# 搜mobile.yangkeduo.com，请求头的AccessToken，设置ddgyck 多账号@分割
export ddgyck='IONFV23L5ZNDQPW7UYE2BEDYA4R32JT5HPENQ3DFFT2LIAQY6DMQ1230e73'
export mkypck='IONFV23L5ZNDQPW7UYE2BEDYA4R32JT5HPENQ3DFFT2LIAQY6DMQ1230e73'

# cron "8 8,14,22 * * *" YongPai.js
# export YongPai="账号1&密码1&支付宝姓名1&支付宝账号1&设备id1 账号2&密码2&支付宝姓名2&支付宝账号2&设备id2"
export YongPai="15165150730&Java1314&袁际范&15165150730&866174010678064 15194115833&Java1314&许倩倩&15194115833&863064011768399 19546115344&Java1314&袁际范&19546115344&354730744091913 19016418104&Java1314&许倩倩&19016418104&863064011510999"

# 福田E家
# cron "39 11,19 * * *" FuTianEJia.js
# export FTEJ="账号1&密码1 账号2&密码2"
export FTEJ="19546115344&Java1314 15165150730&Java1314 15194115833&Java1314 19016418104&Java1314"

# vx爷爷不泡茶
# cron " 10 8,18 * * *" YeYeBuPaoCha.js
# export yybpc= qm-user-token 多账号换行或者#分隔
export yybpc='Fqbz7DpPi18Fs-bm20WWuYwHVBNOy8ai11q_oe7hgpjmVCt3g4UIzyzCY7N8wv0V
mozT_sPrW53UQ6KSVHSraAj68NStw0Us6WrzAKGi_PKD-kty7UHp4xngECJPYDfb
RQO2nnWbdS9tetYNpZIsqleu2DYB4_-WCz4VGk4pOAHuz9VQkPHSthKqHPmKB1CV'

# 找https://mc.kukahome.com/club-server/member/automaticLogin返回值[identityValue@openid@unionnid@1@1@1]
# export GJJJ='identityValue@openid@unionnid@1@1@1参数值'多账号#或&分割
# export SCRIPT_UPDATE = 'False' 关闭脚本自动更新，默认开启
# ✨ ✨ 注意：@1@1@1是固定的 写上就行了
export GJJJ='15165150730@otM6R5Uo310qQTgxyBWKJsHgPCu4@o98mO01OhOS_KZFZj2f4jKg4frdo@1@1@1
19016418104@otM6R5acQmaEkcVlP9Txk3t7Ru_Q@o98mO0wt_aRD_yts48UFq_SnNSSI@1@1@1
19546115344@otM6R5TKpqCMbpp7Huj3O5fV_cxg@o98mO0yKPQ5uUR6QBBm0IHPd83Rw@1@1@1'

# redis-connection-param
export redis_url='192.168.100.3'
export redis_port='6379'
export redis_pwd='foobared'

# @Description: 古井贡酒会员中心小程序
# 变量名 gujing
# 值 https://scrm.gujing.com/gujing_scrm/  请求头Headers中的Access-Token的值 多账号&或换行或新建同名变量
export gujing='eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxLG8zWTJsMUthYmZ2aW9ZbzRLY2YwTVRXQmx1elksb3VBX1k1ZE1lWndVRnJQMXpNeUZGOXhXTUdFSSIsIm9wZW5pZCI6Im91QV9ZNWRNZVp3VUZyUDF6TXlGRjl4V01HRUkiLCJtb2JpbGUiOiIxNTE2NTE1MDczMCIsImV4cCI6MTczMTQ1NzY3NywiYmVsb25nVG9JZCI6MSwiaWF0IjoxNzI4ODY1Njc3LCJtZW1iZXJJZCI6NDY2NDkxNDJ9.I_Fm6pRuQXCmZhfunFml_3q4JlMcP2M8o8tlpryGlQY
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxLG8zWTJsMUNIZFo0LVpqSWU0S0dTdE9QTkFNd1ksb3VBX1k1VWRUUWxzZ0JoS0lHamlwRW1WV2MwUSIsIm9wZW5pZCI6Im91QV9ZNVVkVFFsc2dCaEtJR2ppcEVtVldjMFEiLCJtb2JpbGUiOiIxNTE5NDExNTgzMyIsImV4cCI6MTczMTQ1ODMwMCwiYmVsb25nVG9JZCI6MSwiaWF0IjoxNzI4ODY2MzAwLCJtZW1iZXJJZCI6NDY2NTE4MDh9.LscGGdj2OxLGhCljD7GwbufB3XKYxIey-dKe_bKuN3M
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxLG8zWTJsMVBKaG9VYk10cGl0VHZ4Yk90LXZkR2csb3VBX1k1V3Nyc0lDT2hoLXVwaERkZ2dHeDlmRSIsIm9wZW5pZCI6Im91QV9ZNVdzcnNJQ09oaC11cGhEZGdnR3g5ZkUiLCJtb2JpbGUiOiIxOTU0NjExNTM0NCIsImV4cCI6MTczMTQ1ODYzNCwiYmVsb25nVG9JZCI6MSwiaWF0IjoxNzI4ODY2NjM0LCJtZW1iZXJJZCI6NDY2NDkzNjR9.71lrfYYxa9RDjRtPIeAVjtjkwh5kG8MRRBzbINHLQoI
eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxLG8zWTJsMUMzcVIxbllKNnhCaFBxdDl1V1JDSFUsb3VBX1k1ZFlaS1NCbXBDMUhyblNBXzlpTG9TOCIsIm9wZW5pZCI6Im91QV9ZNWRZWktTQm1wQzFIcm5TQV85aUxvUzgiLCJtb2JpbGUiOiIxOTAxNjQxODEwNCIsImV4cCI6MTczMTQ1ODY5MiwiYmVsb25nVG9JZCI6MSwiaWF0IjoxNzI4ODY2NjkyLCJtZW1iZXJJZCI6NDY2NTA3NzR9.MHULKZttRQ0LHVhklIYTLJjEEOoIajQUSHalm51lsek'

# 中国联通 v2.08
# 首页签到默认运行, 需要关闭的设置变量 chinaUnicomSign='false'
# 联通祝福默认运行, 需要关闭的设置变量 chinaUnicomLtzf='false'
# 定时每天两三次
# 需要在联通APP中选择退出登录-切换账号登录, 捉下面这个包
# https://m.client.10010.com/mobileService/onLine.htm
# 把请求体(body)里面的token_online参数填到变量 chinaUnicomCookie 里, 多账号换行或&或@隔开:
# export chinaUnicomCookie="a3e4c1ff25da2xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
export chinaUnicomCookie='6463c7fdbd87986812620340e0d8028317e8e2f154badd51da93810927c0e38386fc48cfc73d17d03807a2962f62a71cc7825b7bebadfe8c3e67127b9952542aea95b51faacb5388fccf8f493633a697135534bb95970570979563c78abab50c67c49afbcbfc3ed99efc2bfdb9210f6d161116ff5cd85f3234e3007c042dcb7f34dce5dcb1d13cdad6b713897fc5b0fce6de63f070eabf64ec737c4b1c8d32fd5448d9c5c91135ef7709ee2d49afacbcc07e26c8d047f563f9c112ca310fcc166401b1e8ef1e50e953ec538d8b483fba035b7611197380a8c407abb3cb19e43f737af4331c37a2ce7aebd39512137bbc7c645210336630e4ca1a2810981c05ac2c88af33068eca9cf5432bd8346e141df0a9a4a6b6b4f24755e554c471cb06c26d2c0ebf5430bfe6ccbfef8c60d3f7ae'

# 顺丰速运 v3.03
# 包含积分任务, 采蜜游戏, 顺丰会员日
# 采蜜游戏入口: 我的-积分抽奖-拉到最下面收件兑奖
# 积分可以换快递优惠券,爱奇艺周卡,肯德基代金券和各种实物
# 默认不运行采蜜游戏, 需要运行的设置变量 sfsyBee 为true (字符串true)
export sfsyBee="true"
export sfsyUrl='https://mcs-mimp-web.sf-express.com/mcs-mimp/share/weChat/activityRedirect?source=CX&unionId=pohqvB2z%2FHn%2FAaMVvH54AFZT4ivtlm44FgEl5%2FogBC8%3D&openId=weQpAz9zXmrhyiW9TsZs065RYRUxQo174gUpNT%2F6omk%3D&memId=Gp2abcMJpVhXmhHAcUIRHz07YrQCWHeNLTj0CecIU%2FkDdjSr%2F6X0WoiZtgGzs7sG&memNo=6tB15T6k0ZvyroDMcHJm6XZOOeYV8ff4p7m7OUfK8xgDdjSr%2F6X0WoiZtgGzs7sG&mobile=KASjhsnj5dIP16cdbu5ccA%3D%3D&mediaCode=wxapp&bizCode=%7B%22path%22%3A%22%2Fup-member%2FnewPoints%22%2C%22linkCode%22%3A%22SFAC20230803190840424%22%2C%22supportShare%22%3A%22YES%22%2C%22subCategoryCode%22%3A%221%22%2C%22from%22%3A%22mypoint%22%2C%22categoryCode%22%3A%221%22%7D
https://mcs-mimp-web.sf-express.com/mcs-mimp/share/weChat/activityRedirect?source=CX&unionId=D8m2bSCfEgIFutEKGHL1mU2y5lqdMITfXMS4ZXwMijU%3D&openId=z98p5zuVdf10l%2FcSz3rXbmrLVc1SPHLr5ngEWCown7g%3D&memId=ebT8CjYmypb3tRaQeclvge%2B4dvOh7KBNRY8pHlvuFn8DdjSr%2F6X0WoiZtgGzs7sG&memNo=6tB15T6k0ZvyroDMcHJm6SIPOgCV2QCcrHEy0y%2FoGt0DdjSr%2F6X0WoiZtgGzs7sG&mobile=ZNExzyTuh3HdrEPvMHdCPA%3D%3D&mediaCode=wxapp&bizCode=%7B%22path%22%3A%22%2Fup-member%2FnewPoints%22%2C%22linkCode%22%3A%22SFAC20230803190840424%22%2C%22supportShare%22%3A%22YES%22%2C%22subCategoryCode%22%3A%221%22%2C%22from%22%3A%22mypoint%22%2C%22categoryCode%22%3A%221%22%7D
https://mcs-mimp-web.sf-express.com/mcs-mimp/share/weChat/activityRedirect?source=CX&unionId=JKC5vN4r5zCc9ec%2FS4%2Fe6ZdVHKJxYRbv5CoOI8T5XFM%3D&openId=Pl051JRYVG%2BTxBX%2FZXYOIEzNxrZGbGLgawTWlKFPfiE%3D&memId=6UXr8F2zYAacD2D0URtGiE26TOUlTNr1zgl8KF%2BQSSADdjSr%2F6X0WoiZtgGzs7sG&memNo=6tB15T6k0ZvyroDMcHJm6RxjMGij0WlzSJK5OWU2vz8DdjSr%2F6X0WoiZtgGzs7sG&mobile=IPIt0HYH4Sr%2BbJpIPa7XWg%3D%3D&mediaCode=wxapp&bizCode=%7B%22path%22%3A%22%2Fup-member%2FnewPoints%22%2C%22linkCode%22%3A%22SFAC20230803190840424%22%2C%22supportShare%22%3A%22YES%22%2C%22subCategoryCode%22%3A%221%22%2C%22from%22%3A%22mypoint%22%2C%22categoryCode%22%3A%221%22%7D'
#* cron "5 0,18 * * *" YiLi.js
 #* export YiLi='[{"mobile": "1", "openId": "1", "unionId": "1", "nickName": "1", "avatarUrl": "1", "yiliToken":"1"},{"mobile": "2", "openId": "2", "unionId": "2", "nickName": "2", "avatarUrl": "2", "yiliToken":"2"}]'//yiliToken是域名msmarket.msx.digitalyili.com的access-token
 #* export YiLi_Open='true'//翻牌
 export YiLi='[
	 {
		"mobile": "15165150730",
		"openId": "1",
		"unionId": "1",
		"nickName": "1",
		"avatarUrl": "1",
		"yiliToken": "L7+N2Yf10seV+6nyZ3ZNQc5k6SIuydvppQcIkDgZMgOvlidmeQePYYSVuKIrWCFCjrlXwMXEUSiwfbWFXAGcqQnPEQu15XhpUQ9KPEYu8w8="
	},
	{
		"mobile": "15194115833",
		"openId": "2",
		"unionId": "2",
		"nickName": "2",
		"avatarUrl": "2",
		"yiliToken": "M5g0QMjmpae6peNLoj0npTHL+lckLVc2WH2YJZQ3uKs4jujBTsUrmPUTLchU/T8yaf9ah2Op36SgOyxIUcaHtSYNNUi6svGjvIsIpY7Lgcc="
	},
	{
		"mobile": "19016418104",
		"openId": "3",
		"unionId": "3",
		"nickName": "3",
		"avatarUrl": "3",
		"yiliToken": "cesSNrp1bnKrDiyok0fwqNdGA7E12HDwQbGkK1qX3QzhyAhcnwPyyMfr6sUAuB8Dh2fDBNTwXqBsGXO1jQTiS/0qIlvG3aScloOJBRDvheI="
	},
	{
		"mobile": "19546115344",
		"openId": "4",
		"unionId": "4",
		"nickName": "4",
		"avatarUrl": "4",
		"yiliToken": "q9XaZvvnJGvROxqkHa3Kp5LEBdAs2Hd0POVJdKhSx7Qv3FBYyRQy6GXEmdVdVnCK5kTmiG9R05HScMjgQyfYlwbVOeHdRQNzOk2+J8La1Zw="
	}
]'
export YiLi_Open='true'

# 饿了么券
export elmqqck="cookie2=2477ba691039307e9ed911846d8627e2e;unb=2204957856501;USERID=1067001106;SID=MjQ3N2JhNjkxMDM5MzA3ZTllZDkxMTg0NmQ4NjI3ZTJlM8kidb3ry4p2-HqSu8VPCg==;token=1_idc_1_510a3b6019d00da88b46bbd8fa0b73b6556e984560edbc2f919d7e194c01689127e4b8c442a7d17a7818f414a8d181c64057ca237831676e14154ad5ec0c731847f446c69de25399de7f8d52718ec238956c86fb9edd64f7cf3fbf4b70cb20e5a0ff7b4ce34c531fbdf19e23e9fd9d335f0a40aaa572e8bafa14925a912f783f;utdid=ZsQN9mKhFTQDAJsMQP2q+5Eh;deviceId=AoCMs4zNjSqP28_m_ys4HvmU1p3nFVx88iJ3EjeOcYGQ;umt=ySABMApLPGC5EgKSKPY+zWTnI11ic0lq;&
cookie2=120bc962844bddbd3ced5faf3a893a8fe;unb=2204548324719;USERID=175157940;SID=MTIwYmM5NjI4NDRiZGRiZDNjZWQ1ZmFmM2E4OTNhOGZl9v54mdGW-ktGQhipKihKeQ==;token=1_idc_1_3fd73770eeb6bf3743b2bfc05b586b3886353f5bca5c22f66abeba9c87a5f7383b70a4f2055cd3d0533ce8a6fe49fdcd45b357aee806672c9fdb87a12a9e7d0525d33527bf199f1e22c2c2fc9c5c443e77ec57db32ff4f710bd1dad1eae9035d3c77a82da3f78d1cd346131663b3cb78b61f872be6bfca26c105d44d9ae9c458;utdid=ZtU06co8PAMDAJGrdR53quzO;deviceId=AopboEdYtosH4zU4hUDJI2rperQESMmImh5Phh_wZGCo;umt=Ud0B0iZLPHfGogKSKKDjEsPyRPq4qcQm;&
cookie2=1064e67a8415043c4570ac6444106a6de;unb=1919905024743;USERID=3100178576064;SID=MTA2NGU2N2E4NDE1MDQzYzQ1NzBhYzY0NDQxMDZhNmRlg60j1LwPntpgzC6SRglB1A==;token=1_idc_1_b1aebc27eaa43fb7e2324ca417095fd4542b3212bbe980c2bad0f27d2cd969676153b4caba6eb0adaaa31f3525484cd337292d49b1e9716a3c67f805146ec278877355749fc82eac9e615d511c89ba3c18ed82f05b813abb690b842b0dd1851999be1b256a1fa3fce2d3301403b1842eaa7f3d0cb1faf71d8e775ce65ea99576;utdid=ZtVn9RUP/R8DAGh42OLtPonk;deviceId=Al_5xWae22koPzMT7Wxnc_qeZvNtmw82tvBtV_AcsEIY;umt=AvoBOBBLPEWKYAKSQ7a8Uz0kIGn98n7l;"

# cron "30 30,35,40 9 * * *" DaChao.js
# export DaChao="账号1&密码1 账号2&密码2"
# export OCR_SERVER="ocr服务"
export DaChao="19546115344&Java1314 15165150730&Java1314 15194115833&Java1314 19016418104&Java1314"

#微信小程序IQOO社区，抓包authorization，青龙设置变量名iqoo值为authorizatio@要兑换的商品id，抓一次30天有效
#600356，哔哩哔哩 600336，肯德基10元 600335，必胜客20 600334，Qq音乐 600332，腾讯视频
export iqoo='eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiI1ZGY3NDk5YWEyOWFiZmVjN2Q3NzVlZWFlOTBkNTcyYjc4Yjc2YzBkNzg2MmIzOWM3MDg2ZTQ3ZWU4MWIyYzcyYjAyZTBiZjEzOWIxYmEzNCIsImlhdCI6MTcyNjgxMDkwNSwibmJmIjoxNzI2ODEwOTA1LCJleHAiOjE3Mjk0MDI5MDUsInN1YiI6IjU3NTEyMTgiLCJzY29wZXMiOltudWxsXX0.D4L8ma4-UbY8ghorQJ5viyWcR_pIJwdGp9ndqjIl6Xl6Jxs7Mzi37zAaYuIj-yE2XtGCNwe7Iq3z9mRz6PZZkM0BzHP_1tbWjT4X-i1qwWb8J7Ipfi3HgYC45soy3TgyKwoIOxgrAr_l1AIJmIEYVTKuBn8MhZ8MFDDjlcX9IMXsWmwACVoWvS_jfCZMWPJQLE3eYzuha8cHZnXioXrhhsqkG9guB8F4P0GhhSDCE8DX7Xi6NBIkEDkcj88Lyp497pn-kAi1rgTYHY05qy4RN8z5TQgIR-an3R9MZTLz0kWf-m6BMr9moHBRSSwOlA-FCVV4ENLdiWPmSjz9xnS6WQ@600356
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiJmYjRlY2VhZTI1YTMxNmNjMTFkMzRlYTgyODQ5MzBmZjMyZTBhYjZhMzQxZWM1ZTVkOGFkNmQ3YmM5ZWMyZmJmMDA2ZWYwYzE4OWY5M2RmOCIsImlhdCI6MTcyNzQyODQwMCwibmJmIjoxNzI3NDI4NDAwLCJleHAiOjE3MzAwMjA0MDAsInN1YiI6IjYwNDY3NzMiLCJzY29wZXMiOltudWxsXX0.efhHI4WG6cUr6BxVFTKFF8cXHUo6wwqsOpjfkIfn7raiLSAhRaU8RzcqEEiwhbyJVACYBy5obuvG5JXDTTaUoA_5DZ7G_JmxDpehab_GKpRclu3Q28AjFnpMrhw-K1cfw95d1D9uYcZTjlWHUSxypdalF8a8kkBv_KVd38foZQN-bSBVqy21ifg2-i7Rx0Ln1HK1gJjT2x1KWnVqV851aqHXRhy-zTEiZIt_Lq0BAJk_8DO1r3M_PX0IRB5CJVoYTNRzH4El0ZCViAr4970y6xdc58lxEtJTVtJNuoAlNuQ1qqVCWxFJX-B58Nlz4piriG-UJqTKWan3sRYPi40KHQ@600356
eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiIxZThlNzhlYmUwOGI5ZTFmZTI2NzliZDhlMWI4M2NlZGU2NjYwMjVkNDEwMGIwNDVmODBjZGQwMDY5OGIwYzQ0YWUxOTM0NDQ3MWM1MWI3MyIsImlhdCI6MTcyNzQyOTAzMywibmJmIjoxNzI3NDI5MDMzLCJleHAiOjE3MzAwMjEwMzMsInN1YiI6IjYwNDY4MDkiLCJzY29wZXMiOltudWxsXX0.hXGMKXMG9KupEfs-32kdNJ50bbhcWa8CFISIOXz0ODiT0dJXCFB5c2oaK7bBQK2YvNa2i9pUI1iuriYeXT1E5DduTsqEnTVs5uBmrLghh2T-mPB_kEH9iM7gRBaVocN9J-o_8FyQXT6oN3fFy7ReGJ7TBHxrUPFHN2QL_OwG1RB53dr5Pk_n918kdTBF7Pg0XZCxgb8h6H2EnKXMY0Ltvpcy71a1o_JoJt4qx3HaMMZBvqIf4_TOlLouGrAp_FtkkxGkUFnPDkjrNq1hDrp2iqVVxr-N7ykzPdZJm4ADW9kRPgm4rv8H20kyvhA_v4lA4NnfAF8YA9oBRYqp-XSnqA@600356'

 # cron "25 9 * * *" IQOO.js
 # '[{"id": "1", "xVisitor": "1", "token": "1"},{"id": "2", "xVisitor": "2", "token": "2"}]'
 # export IQOO_Create="true"//发帖
export IQOO_Create="true"
export IQOO='[
	{
		"id": "1",
		"xVisitor": "1",
		"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiI1ZGY3NDk5YWEyOWFiZmVjN2Q3NzVlZWFlOTBkNTcyYjc4Yjc2YzBkNzg2MmIzOWM3MDg2ZTQ3ZWU4MWIyYzcyYjAyZTBiZjEzOWIxYmEzNCIsImlhdCI6MTcyNjgxMDkwNSwibmJmIjoxNzI2ODEwOTA1LCJleHAiOjE3Mjk0MDI5MDUsInN1YiI6IjU3NTEyMTgiLCJzY29wZXMiOltudWxsXX0.D4L8ma4-UbY8ghorQJ5viyWcR_pIJwdGp9ndqjIl6Xl6Jxs7Mzi37zAaYuIj-yE2XtGCNwe7Iq3z9mRz6PZZkM0BzHP_1tbWjT4X-i1qwWb8J7Ipfi3HgYC45soy3TgyKwoIOxgrAr_l1AIJmIEYVTKuBn8MhZ8MFDDjlcX9IMXsWmwACVoWvS_jfCZMWPJQLE3eYzuha8cHZnXioXrhhsqkG9guB8F4P0GhhSDCE8DX7Xi6NBIkEDkcj88Lyp497pn-kAi1rgTYHY05qy4RN8z5TQgIR-an3R9MZTLz0kWf-m6BMr9moHBRSSwOlA-FCVV4ENLdiWPmSjz9xnS6WQ"
	},
	{
		"id": "2",
		"xVisitor": "2",
		"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiJmYjRlY2VhZTI1YTMxNmNjMTFkMzRlYTgyODQ5MzBmZjMyZTBhYjZhMzQxZWM1ZTVkOGFkNmQ3YmM5ZWMyZmJmMDA2ZWYwYzE4OWY5M2RmOCIsImlhdCI6MTcyNzQyODQwMCwibmJmIjoxNzI3NDI4NDAwLCJleHAiOjE3MzAwMjA0MDAsInN1YiI6IjYwNDY3NzMiLCJzY29wZXMiOltudWxsXX0.efhHI4WG6cUr6BxVFTKFF8cXHUo6wwqsOpjfkIfn7raiLSAhRaU8RzcqEEiwhbyJVACYBy5obuvG5JXDTTaUoA_5DZ7G_JmxDpehab_GKpRclu3Q28AjFnpMrhw-K1cfw95d1D9uYcZTjlWHUSxypdalF8a8kkBv_KVd38foZQN-bSBVqy21ifg2-i7Rx0Ln1HK1gJjT2x1KWnVqV851aqHXRhy-zTEiZIt_Lq0BAJk_8DO1r3M_PX0IRB5CJVoYTNRzH4El0ZCViAr4970y6xdc58lxEtJTVtJNuoAlNuQ1qqVCWxFJX-B58Nlz4piriG-UJqTKWan3sRYPi40KHQ"
	},
	{
		"id": "3",
		"xVisitor": "3",
		"token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIiLCJqdGkiOiIxZThlNzhlYmUwOGI5ZTFmZTI2NzliZDhlMWI4M2NlZGU2NjYwMjVkNDEwMGIwNDVmODBjZGQwMDY5OGIwYzQ0YWUxOTM0NDQ3MWM1MWI3MyIsImlhdCI6MTcyNzQyOTAzMywibmJmIjoxNzI3NDI5MDMzLCJleHAiOjE3MzAwMjEwMzMsInN1YiI6IjYwNDY4MDkiLCJzY29wZXMiOltudWxsXX0.hXGMKXMG9KupEfs-32kdNJ50bbhcWa8CFISIOXz0ODiT0dJXCFB5c2oaK7bBQK2YvNa2i9pUI1iuriYeXT1E5DduTsqEnTVs5uBmrLghh2T-mPB_kEH9iM7gRBaVocN9J-o_8FyQXT6oN3fFy7ReGJ7TBHxrUPFHN2QL_OwG1RB53dr5Pk_n918kdTBF7Pg0XZCxgb8h6H2EnKXMY0Ltvpcy71a1o_JoJt4qx3HaMMZBvqIf4_TOlLouGrAp_FtkkxGkUFnPDkjrNq1hDrp2iqVVxr-N7ykzPdZJm4ADW9kRPgm4rv8H20kyvhA_v4lA4NnfAF8YA9oBRYqp-XSnqA"
	}
]'

#   入口:巴奴火锅小程序
#
#   需抓取数据:
#   * 抓取member_id的值填入
#   #   变量名:yuanshen_bnhg
#   多号分割方式 [ @ 或 换行 或 新建同名变量 ]
export yuanshen_bnhg='ae85b57ecfee46b68dd40425a9dde31f'

# 霸王茶姬-微信小程序
# export bwcj_ck="token#userId"  多个换行
# 8104#5344
export bwcj_ck='i1KRsXxq8JT23BXj-bI0JEfaWosstap-laOstaD1gT6YoGCKt5BIrqbidPHqvuUi#1043584100904214528
Vj2GapjIvnCZDfnIgu9PYIbmvDvgwAJt9Rt-3V1-YJYfD77KqRJ4M5VgehTosjzA#1043582132064251904'

# new Env('中国人保');
#by-莫老师，版本2.0
#cron:5 1 * * *
#打开https://e.picc.com/piccapp/install/register.html?app=1&uuIdFlag=2a05f9fa-8下载app，然后用微信登陆抓包，抓thirdPartyId和deviceid的值，青龙设置变量名zgrbck，值为thirdPartyId@deviceid。一次抓包永久有效
export zgrbck='oF3RGt7Qn6Hkf-8kcEhvVKOhwP8U@9e9e8a1d-fb24-38de-a5d1-3b825dc151d5'

# 中国人保-养鸡
# 变量名: ZGRBYJ
# cron: 0 7,12,23,0 * * *
export ZGRBYJ='02772ef0936780f68d40ba1283d3ff65'
export rbxj='02772ef0936780f68d40ba1283d3ff65'

# 微信小程序picc爱心农场nongchang.maxrocky.com，抓包skey，青龙创建变量rbnc，值为skey
export rbnc='5cef34609a37a291712ab45c985062d8'

## 项目名称 海尔智家
## export hezj_data='token&clientId @ token&clientId '
## 多账号用 换行 或 @ 分割
## 抓包 https://mps.haiersmarthomes.com/api-gw , 找到accounttoken/accesstoken & clientId即可
export hezj_data='1317e58024ca465f898f5b5b75bcf828&3C1C103D9E34C54BCFD52BDD132D49D4'

## 习酒会员俱乐部
## export XiJiu='[{"id": "1", "loginCode": "1"},{"id": "2", "loginCode": "2"}]'
## export XiJiu_Exchange='true'//酒换积分
## export OCR_SERVER="ocr服务"
export XiJiu='[
	{
		"id": "8104",
		"loginCode": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbmlkIjoib0E0b0QxYWJxclRxMjZlZVV3ajh0RzNvQXFUYyIsInVzZXJfaWQiOjAsImV4cGlyZSI6MTcyNjUzNzA3N30.10K2Z0o8o4zcP_anM2HT2DGxU_zHuN_JwjrTcjq9HDc"
	},{
		"id": "0730",
		"loginCode": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbmlkIjoib0E0b0QxVnFKakxXeEhXX2FlMmtVWHRHSDdvdyIsInVzZXJfaWQiOjAsImV4cGlyZSI6MTcyODgzMzMyNH0.rSLMOWNW_we5Gulhs8BP8LiPbGXWCkqKdC3sPvJpj-E"
	},
	{
		"id": "5833",
		"loginCode": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbmlkIjoib0E0b0QxY2k4b2lraXRDRTVhcjFqQWRhMlV3RSIsInVzZXJfaWQiOjAsImV4cGlyZSI6MTcyNjgxMTI5OH0.mo-UDzOgGHqxTJ-wGwqCATbZWR3r0lQ-RbxicsWEQd8"
	},
	{
		"id": "5344",
		"loginCode": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1bmlvbmlkIjoib0E0b0QxVDFCVmJqYUwyamcxWUFJVHRvcHh1RSIsInVzZXJfaWQiOjAsImV4cGlyZSI6MTcyNjYyMzk5MX0.oKEm3ZTNSPi5m0ObyPv8J562NRHnM2qC7m9QxChHXWI"
	}
]'

export XiJiu_phone=true
export XiJiu_Exchange='true'
export OCR_SERVER="https://ddddocr.xzxxn7.live"
## 君品荟
## export JunPinHui="账号1&密码1 账号2&密码2"
export JunPinHui="19016418104&Java_1314 19546115344&Java_1314 15194115833&Java_1314"

 ## cron "30 10,18 * * *" QingDaoNews.js
 ## export QingDaoNews="账号1&密码1 账号2&密码2"
export QingDaoNews="15165150730&Java1314 19546115344&Java1314"

## 望潮APP
## export WangChao="账号1&密码1 账号2&密码2"
export WangChao="19546115344&Java1314 19016418104&Java1314"

 ## cron 9 9 * * *  XinXi.py
 ## 变量名: XSSONF
 ## 变量值:api.xinc818.com 请求头中sso的值 多账户&或者换行
export XSSONF="Wmeimob_eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzYwMjE0IywjMSMsIzIyMy4xMDQuNDEuNSMsIzdmZWE1YmMwZThkNjQ2YTQ5NmZiYjliYTZiNDRlOGRiIiwiaXNzIjoiYXV0aDAiLCJleHAiOjMyMTg2Nzk3NDl9.YeJ-rGWRAABwoy2MxdZGodCMkPgC423HOWkhNKQ6idA#0730
Wmeimob_eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzIxMDg2IywjMSMsIzExMi4yMjkuMTAyLjI0NCMsIzIyZjhlNTZlMDhmZTRkZGJiZmU1MTQ1YTg5ODMzOTk1IiwiaXNzIjoiYXV0aDAiLCJleHAiOjMyMTYxODk3NDV9.K6p3DkWRYGcNITfmrKcxv8fdWY8IOduvsxs8xhd5Zs4#2
Wmeimob_eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNzIyNDQ1IywjMSMsIzExMi4yMjkuMTAyLjI0NCMsI2I5MWQwM2NiNzQ4MjRmNWM4MzJlNjk5N2M4Zjg2Nzg5IiwiaXNzIjoiYXV0aDAiLCJleHAiOjMyMTYyMTIwNjV9.v_zAx7YffilecdvQjcOiX_-FWCz6wO427te5LfyCCQ4#3"

## WxPusher-风火轮
export WP_APP_TOKEN_ONE="AT_Z5U3t2kky7uDc7Ss81qat0cyDqzsNDSj"

## 可乐
export push_config="{'printf':0,'threadingf':1,'threadingt':3,'appToken':'AT_Z5U3t2kky7uDc7Ss81qat0cyDqzsNDSj'}"
## 在运行 ql repo 命令时，是否自动删除失效的脚本与定时任务
AutoDelCron="true"

## 在运行 ql repo 命令时，是否自动增加新的本地定时任务
AutoAddCron="true"

## 拉取脚本时默认的定时规则，当匹配不到定时规则时使用，例如: 0 9 * * *
DefaultCronRule=""

## ql repo命令拉取脚本时需要拉取的文件后缀，直接写文件后缀名即可
RepoFileExtensions="js mjs py sh pyc ts"

## 代理地址，支持HTTP/SOCK5，例如 http://127.0.0.1:7890
#ProxyUrl="http://192.168.100.3:9992"
ProxyUrl=""

## 资源告警阙值，默认CPU 80%、内存80%、磁盘90%
CpuWarn=80
MemoryWarn=80
DiskWarn=90

## 设置定时任务执行的超时时间，例如1h，后缀"s"代表秒(默认值), "m"代表分, "h"代表小时, "d"代表天
CommandTimeoutTime=""

## 在运行 task 命令时，随机延迟启动任务的最大延迟时间，如 RandomDelay="300" ，表示任务将在 1-300 秒内随机延迟一个秒数，然后再运行，取消延迟赋值为空
RandomDelay=""

## 需要随机延迟运行任务的文件后缀，直接写后缀名即可，多个后缀用空格分开，例如: js py ts
## 默认仅给javascript任务加随机延迟，其它任务按定时规则准点运行。全部任务随机延迟赋值为空
RandomDelayFileExtensions=""

## 每小时的第几分钟准点运行任务，当在这些时间运行任务时将忽略 RandomDelay 配置，不会被随机延迟
## 默认是第0分钟和第30分钟，例如21:00或21:30分的任务将会准点运行。不需要准点运行赋值为空
RandomDelayIgnoredMinutes=""

## 如果你自己会写shell脚本，并且希望在每次容器启动时，额外运行你的 shell 脚本，请赋值为 "true"
EnableExtraShell=""

## 是否自动启动bot，默认不启动，设置为true时自动启动，目前需要自行克隆bot仓库所需代码，存到ql/repo目录下，文件夹命名为dockerbot
AutoStartBot=""

## 是否使用第三方bot，默认不使用，使用时填入仓库地址，存到ql/repo目录下，文件夹命名为diybot
BotRepoUrl=""

## 通知环境变量
## 1. Server酱
## https://sct.ftqq.com/r/13363
## 下方填写 SCHKEY 值或 SendKey 值
export PUSH_KEY=""

## 2. BARK
## 下方填写app提供的设备码，例如：https://api.day.app/123 那么此处的设备码就是123
export BARK_PUSH=""
## 下方填写推送图标设置，自定义推送图标(需iOS15或以上)
export BARK_ICON="https://qn.whyour.cn/logo.png"
## 下方填写推送声音设置，例如choo，具体值请在bark-推送铃声-查看所有铃声
export BARK_SOUND=""
## 下方填写推送消息分组，默认为"QingLong"
export BARK_GROUP="QingLong"
## bark 推送时效性
export BARK_LEVEL="active"
## bark 推送是否存档
export BARK_ARCHIVE=""
## bark 推送跳转 URL
export BARK_URL=""

## 3. Telegram
## 下方填写自己申请@BotFather的Token，如10xxx4:AAFcqxxxxgER5uw
export TG_BOT_TOKEN=""
## 下方填写 @getuseridbot 中获取到的纯数字ID
export TG_USER_ID=""
## Telegram 代理IP（选填）
## 下方填写代理IP地址，代理类型为 http，比如您代理是 http://127.0.0.1:1080，则填写 "127.0.0.1"
## 如需使用，请自行解除下一行的注释
export TG_PROXY_HOST=""
## Telegram 代理端口（选填）
## 下方填写代理端口号，代理类型为 http，比如您代理是 http://127.0.0.1:1080，则填写 "1080"
## 如需使用，请自行解除下一行的注释
export TG_PROXY_PORT=""
## Telegram 代理的认证参数（选填）
export TG_PROXY_AUTH=""
## Telegram api自建反向代理地址（选填）
## 教程：https://www.hostloc.com/thread-805441-1-1.html
## 如反向代理地址 http://aaa.bbb.ccc 则填写 aaa.bbb.ccc
## 如需使用，请赋值代理地址链接，并自行解除下一行的注释
export TG_API_HOST=""

## 4. 钉钉
## 官方文档：https://developers.dingtalk.com/document/app/custom-robot-access
## 下方填写token后面的内容，只需 https://oapi.dingtalk.com/robot/send?access_token=XXX 等于=符号后面的XXX即可
export DD_BOT_TOKEN=""
export DD_BOT_SECRET=""

## 企业微信反向代理地址
## (环境变量名 QYWX_ORIGIN)
export QYWX_ORIGIN=""

## 5. 企业微信机器人
## 官方说明文档：https://work.weixin.qq.com/api/doc/90000/90136/91770
## 下方填写密钥，企业微信推送 webhook 后面的 key
export QYWX_KEY=""

## 6. 企业微信应用
## 参考文档：http://note.youdao.com/s/HMiudGkb
## 下方填写素材库图片id（corpid,corpsecret,touser,agentid），素材库图片填0为图文消息, 填1为纯文本消息
export QYWX_AM=""

## 7. iGot聚合
## 参考文档：https://wahao.github.io/Bark-MP-helper
## 下方填写iGot的推送key，支持多方式推送，确保消息可达
export IGOT_PUSH_KEY=""

## 8. Push Plus
## 官方网站：http://www.pushplus.plus
## 下方填写您的Token，微信扫码登录后一对一推送或一对多推送下面的token，只填 PUSH_PLUS_TOKEN 默认为一对一推送
export PUSH_PLUS_TOKEN=""
## 一对一多推送（选填）
## 下方填写您的一对多推送的 "群组编码" ，（一对多推送下面->您的群组(如无则新建)->群组编码）
## 1. 需订阅者扫描二维码 2、如果您是创建群组所属人，也需点击“查看二维码”扫描绑定，否则不能接受群组消息推送
export PUSH_PLUS_USER=""

## 9. 微加机器人
## 官方网站：http://www.weplusbot.com
## 下方填写您的Token；微信扫描登录后在"我的"->"设置"->"令牌"中获取
export WE_PLUS_BOT_TOKEN=""
## 消息接收人；
## 个人版填写接收消息的群编码，不填发送给自己的微信号
## 专业版不填默认发给机器人自己，发送给好友填写wxid，发送给微信群填写群编码
export WE_PLUS_BOT_RECEIVER=""
## 调用版本；分为专业版和个人版，专业版填写pro，个人版填写personal
export WE_PLUS_BOT_VERSION="pro"

## 10. go-cqhttp
## gobot_url 推送到个人QQ: http://127.0.0.1/send_private_msg  群：http://127.0.0.1/send_group_msg
## gobot_token 填写在go-cqhttp文件设置的访问密钥
## gobot_qq 如果GOBOT_URL设置 /send_private_msg 则需要填入 user_id=个人QQ 相反如果是 /send_group_msg 则需要填入 group_id=QQ群
## go-cqhttp相关API https://docs.go-cqhttp.org/api
export GOBOT_URL=""
export GOBOT_TOKEN=""
export GOBOT_QQ=""

## 11. gotify
## gotify_url 填写gotify地址,如https://push.example.de:8080
## gotify_token 填写gotify的消息应用token
## gotify_priority 填写推送消息优先级,默认为0
export GOTIFY_URL=""
export GOTIFY_TOKEN=""
export GOTIFY_PRIORITY=0

## 12. PushDeer
## deer_key 填写PushDeer的key
export DEER_KEY=""

## 13. Chat
## chat_url 填写synology chat地址，http://IP:PORT/webapi/***token=
## chat_token 填写后面的token
export CHAT_URL=""
export CHAT_TOKEN=""

## 14. aibotk
## 官方说明文档：http://wechat.aibotk.com/oapi/oapi?from=ql
## aibotk_key (必填)填写智能微秘书个人中心的apikey
export AIBOTK_KEY=""
## aibotk_type (必填)填写发送的目标 room 或 contact, 填其他的不生效
export AIBOTK_TYPE=""
## aibotk_name (必填)填写群名或用户昵称，和上面的type类型要对应
export AIBOTK_NAME=""

## 15. CHRONOCAT
## CHRONOCAT_URL 推送 http://127.0.0.1:16530
## CHRONOCAT_TOKEN 填写在CHRONOCAT文件生成的访问密钥
## CHRONOCAT_QQ 个人:user_id=个人QQ 群则填入group_id=QQ群 多个用英文;隔开同时支持个人和群 如：user_id=xxx;group_id=xxxx;group_id=xxxxx
## CHRONOCAT相关API https://chronocat.vercel.app/install/docker/official/
export CHRONOCAT_URL=""
export CHRONOCAT_QQ=""
export CHRONOCAT_TOKEN=""

## 16. SMTP
## 邮箱服务名称，比如126、163、Gmail、QQ等，支持列表 https://github.com/nodemailer/nodemailer/blob/master/lib/well-known/services.json
export SMTP_SERVICE=""
## smtp_email 填写 SMTP 收发件邮箱，通知将会由自己发给自己
export SMTP_EMAIL=""
## smtp_password 填写 SMTP 登录密码，也可能为特殊口令，视具体邮件服务商说明而定
export SMTP_PASSWORD=""
## smtp_name 填写 SMTP 收发件人姓名，可随意填写
export SMTP_NAME=""

## 17. PushMe
## 官方说明文档：https://push.i-i.me/
## PUSHME_KEY (必填)填写PushMe APP上获取的push_key
## PUSHME_URL (选填)填写自建的PushMeServer消息服务接口地址，例如：http://127.0.0.1:3010，不填则使用官方接口服务
export PUSHME_KEY=""
export PUSHME_URL=""

## 18. 飞书机器人
## 官方文档：https://www.feishu.cn/hc/zh-CN/articles/360024984973
## FSKEY 飞书机器人的 FSKEY
export FSKEY=""

## 19. Qmsg酱
## 官方文档：https://qmsg.zendee.cn/docs/api/
## qmsg 酱的 QMSG_KEY
## qmsg 酱的 QMSG_TYPE send 为私聊，group 为群聊
export QMSG_KEY=""
export QMSG_TYPE=""

## 20. 自定义通知
## 自定义通知 接收回调的URL
export WEBHOOK_URL=""
## WEBHOOK_BODY 和 WEBHOOK_HEADERS 多个参数时，直接换行或者使用 $'\n' 连接多行字符串，比如 export dd="line 1"$'\n'"line 2"
export WEBHOOK_BODY=""
export WEBHOOK_HEADERS=""
## 支持 GET/POST/PUT
export WEBHOOK_METHOD=""
## 支持 text/plain、application/json、multipart/form-data、application/x-www-form-urlencoded
export WEBHOOK_CONTENT_TYPE=""

## 其他需要的变量，脚本中需要的变量使用 export 变量名= 声明即可
