# [垃圾分类]
通过垃圾名称在数据库查询垃圾分类，没查到就采用HttpClient抓取其他网站的查询结果，有结果则存入自己的数据库，
再返回给web层一个加密过数据，加密方式是自创的MorseZP加密算法，再再前端解密进行展示。若无结果则反馈此垃圾等待后续更新词库。
