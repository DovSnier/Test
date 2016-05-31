
-- DROP TABLE IF EXISTS "main"."android_metadata";
-- CREATE TABLE android_metadata (locale TEXT);
-- INSERT INTO "main"."android_metadata" VALUES ('zh_CN');

-- DROP TABLE IF EXISTS "main"."notes";
CREATE TABLE notes (_id INTEGER PRIMARY KEY,word TEXT,spell TEXT,detail TEXT,created INTEGER,isdeleted INTEGER,phonetic TEXT,username TEXT,rem_status INTEGER DEFAULT(-1),rem_time TEXT,rem_sync_status INTEGER);

-- ----------------------------
-- Records of notes
-- ----------------------------
INSERT INTO "main"."notes" VALUES (1, 'alchemy', 'alchemy', 'n. 点金术；魔力', 1448854177000, 0, '''ælkɪmɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (2, 'purge', 'purge', 'vi. 净化；通便
vt. 净化；清洗；通便
n. 净化；泻药
', 1460554150872, 0, 'pɝdʒ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (3, 'anarchy', 'anarchy', 'n. 无政府状态；混乱；无秩序
', 1460554175940, 0, '''ænɚki', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (4, 'rally', 'rally', 'vi. 团结；重整；恢复；（网球等）连续对打
vt. 团结；集合；恢复健康、力量等
n. 集会；回复；公路赛车会
n. (Rally)人名；(罗)拉利
', 1460554707300, 0, '''ræli', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (5, 'stock exchange', 'stock exchange', '证券交易所
', 1460554753879, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (6, 'bust', 'bust', 'vi. 破产；爆裂；降低级别
vt. 使破产；使爆裂；逮捕
n. 破产；半身像；萧条；胸部
adj. 破产了的；毁坏了的
n. (Bust)人名；(德)布斯特
', 1460554826760, 0, 'bʌst', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (7, 'volatile', 'volatile', 'adj. [化学] 挥发性的；不稳定的；爆炸性的；反覆无常的
n. 挥发物；有翅的动物
n. (Volatile)人名；(意)沃拉蒂莱
', 1460555018655, 0, '''vɑlətl', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (8, 'race', 'race', 'n. 属，种；种族，人种；家庭，门弟
vt. 使参加比赛；和…竞赛；使急走，使全速行进
vi. 比速度，参加竞赛；全速行进
n. (Race)人名；(英)雷斯；(塞)拉采
', 1460555077614, 0, 'res', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (9, 'stacked', 'stacked', 'adj. 妖艳的；（女人）身材丰满匀称的
v. 堆放（stack的过去分词）
', 1460555189560, 0, 'stækt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (10, 'fallout', 'fallout', 'n. 原子尘；[核][环境] 放射性尘埃；原子尘微粒回降；附带结果
', 1460555485098, 0, '''fɔlaʊt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (11, 'grill', 'grill', 'vt. 烧，烤；
vi. 拷问，严加盘问
n. 烤架，铁格子；烤肉
', 1460555681895, 0, 'ɡrɪl', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (12, 'lawsuit', 'lawsuit', 'n. 诉讼（尤指非刑事案件）；诉讼案件
', 1460571270018, 0, '''lɔsut', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (13, 'outsourcing', 'outsourcing', 'n. 外包；外购；外部采办
', 1460596588923, 0, '''aʊt,sɔrsɪŋ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (14, 'town hall', 'town hall', '市政厅；市政府；市民集会所
', 1460596767536, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (15, 'dispatcher', 'dispatcher', 'n. 调度员；[计] 调度程序；[计] 分配器
', 1460596945450, 0, 'dɪ''spætʃɚ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (16, 'attic', 'attic', 'n. 阁楼；顶楼；鼓室上的隐窝
', 1460596995811, 0, '''ætɪk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (17, 'lunch', 'lunch', 'n. 午餐
vt. 吃午餐；供给午餐
vi. 吃午餐；供给午餐
', 1460597422473, 0, 'lʌntʃ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (18, '粘度', 'zhandu', '[物] viscosity
', 1460605841458, 0, 'nián dù', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (19, 'video', 'video', 'n. [电子] 视频；录像，录像机；电视
adj. 视频的；录像的；电视的
v. 录制
', 1460686410118, 0, '''vɪdɪo', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (20, 'rust', 'rust', 'n. 锈；生锈；[植保] 锈病
vt. 使生锈；腐蚀
vi. 生锈；成铁锈色；变迟钝
n. (Rust)人名；(英)拉斯特；(德、捷、瑞典)鲁斯特；(法)吕斯特
', 1460704508228, 0, 'rʌst', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (21, 'primer', 'primer', 'n. 初级读本；识字课本；原始物
', 1460705594662, 0, '''praɪmɚ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (22, 'milliliter', 'milliliter', 'n. 毫升
', 1460706602932, 0, '''mɪlɪˌlitɚ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (23, 'efficient', 'efficient', 'adj. 有效率的；有能力的；生效的
', 1460714652025, 0, 'ɪ''fɪʃnt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (24, 'pier', 'pier', 'n. 码头，直码头；桥墩；窗间壁
n. (Pier)人名；(英、德)皮尔；(西)彼尔；(意)皮耶尔
', 1461284462477, 0, 'pɪr', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (25, 'green hand', 'green hand', 'n. 生手；没有经验的人
', 1461284490488, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (26, 'briny', 'briny', 'adj. 海水的；咸的；盐水的
', 1462159105921, 0, '''braɪni', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (27, 'creak', 'creak', 'n. 嘎吱嘎吱声
vi. 发出咯吱咯吱声；勉强运转
vt. 使咯吱咯吱响
n. (Creak)人名；(英)克里克
', 1462159131015, 0, 'krik', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (28, 'groan', 'groan', 'vi. 呻吟；抱怨；发吱嘎声
vt. 呻吟；抱怨
n. 呻吟；叹息；吱嘎声
', 1462159140762, 0, 'ɡron', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (29, 'quadrille', 'quadrille', 'n. 四对方舞；四对方舞曲
adj. 有方格的；有小方格图案的
', 1462159191149, 0, 'kwə''drɪl', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (30, 'plaid', 'plaid', 'n. 格子花呢；格子图案
adj. 有格子图案的
', 1462159202126, 0, 'plæd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (31, 'dynamite', 'dynamite', 'n. 炸药；具有潜在危险的人（或物）
vt. 炸毁
adj. 极好的
', 1462590828868, 0, '''daɪnə''maɪt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (32, 'mako', 'mako', 'n. （大西洋和太平洋发现的）条纹状鲨鱼
n. (Mako)人名；(塞、保)马科；(日)真子(姓)
', 1462590874036, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (33, 'rugged', 'rugged', 'adj. 崎岖的；坚固的；高低不平的；粗糙的
', 1462594097471, 0, '''rʌɡɪd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (34, 'contest', 'contest', 'vt. 争辩；提出质疑
vi. 竞争；争辩
n. 竞赛；争夺；争论
', 1463055042482, 0, 'kən''tɛst', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (35, 'famished', 'famished', 'adj. 极饥饿的
v. 使饥饿（famish的过去式）
', 1463630946241, 0, '''fæmɪʃt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (36, 'crag', 'crag', 'n. 峭壁；岩石碎块；颈；嗉囊
', 1464442452073, 0, 'kræg', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (37, 'mutineer', 'mutineer', 'n. 反抗者；叛变者
', 1464442616950, 0, 'mjuːtɪ''nɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (38, 'awry', 'awry', 'adj. 错误的；扭曲的
adv. 歪曲地；歪斜地；错误地
', 1464443028163, 0, 'ə''raɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (39, 'enervate', 'enervate', 'vt. 使衰弱，使失去活力
adj. 衰弱的，无力的
', 1464443348471, 0, 'ˈenərveɪt;ɪˈnɜːveɪt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (40, 'purblind', 'purblind', 'adj. 半盲的；愚钝的；惺松眼的
vt. 使成半瞎
', 1464443504815, 0, '''pɝ,blaɪnd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (41, 'wobble', 'wobble', 'vi. 摇晃；摇摆；游移不定
n. 摆动；摇晃；不稳定
vt. 使摇摆；使颤动；摇动', 1446103320000, 0, '''wɒb(ə)l', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (42, 'skid', 'skid', 'n. 打滑；[车辆] 刹车；滑轨，滑动垫木
vt. 刹住，使减速；滚滑
vi. 打滑', 1445918040000, 0, 'skɪd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (43, 'calcium', 'calcium', 'n. [化学] 钙', 1444212971000, 0, '''kælsɪəm', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (44, 'lattices', 'lattices', 'n. 格子；点阵；车削；格栅式门窗；控制格线（lattice的复数）', 1444180476000, 0, '''lætɪs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (45, 'pasty', 'pasty', 'adj. 苍白的；面糊似的
n. 馅饼', 1451044460000, 0, '''pæstɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (46, 'salaam', 'salaam', 'n. 问安；额手礼
vt. 向…行额手礼；向…问候
vi. 行额手礼；问候', 1444181907000, 0, 'sə''lɑːm', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (47, 'oxidant', 'oxidant', 'n. 氧化剂', 1444095722000, 0, '''ɒksɪd(ə)nt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (48, 'EC', 'ec', 'abbr. 欧共体（European Community）；接地电流（Earth Current）；电子计算机（Electronic Computer）；弹性系数（Elasticity Coefficient）', 1452408173000, 0, ',i ''si', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (49, 'hiatus', 'hiatus', 'n. 裂缝，空隙；脱漏部分', 1444091450000, 0, 'haɪ''eɪtəs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (50, 'manifold', 'manifold', 'vt. 复写，复印；增多；使……多样化
adj. 多方面的，有许多部分的；各式各样的
n. 多种；复印本', 1445925445000, 0, '''mænɪfəʊld', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (51, 'calamity', 'calamity', 'n. 灾难；不幸事件', 1444212844000, 0, 'kə''læmɪtɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (52, 'ozonosphere', 'ozonosphere', 'n. [地物] 臭氧层（等于ozone layer）', 1444095497000, 0, 'əʊ''zəʊnəsfɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (53, 'amateur', 'amateur', 'n. 爱好者；业余爱好者；外行
adj. 业余的；外行的', 1444114155000, 0, '''æmətə; -tʃə; -tjʊə; ,æmə''tɜː', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (54, 'carpet', 'carpet', 'vt. 在…上铺地毯，把地毯铺在…上；斥责
n. 地毯；地毯状覆盖物', 1444966632000, 0, '''kɑːpɪt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (55, 'oakum', 'oakum', 'n. 麻絮；填絮（用于填塞船缝等）', 1444095219000, 0, '''əʊkəm', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (56, 'olive', 'olive', 'n. 橄榄；橄榄树；橄榄色
adj. 橄榄的；橄榄色的', 1444094719000, 0, '''ɒlɪv', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (57, 'erratic', 'erratic', 'adj. 不稳定的；古怪的
n. 漂泊无定的人；古怪的人', 1444178793000, 0, 'ɪ''rætɪk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (58, 'hose', 'hose', 'n. 软管；长统袜；男性穿的紧身裤
vt. 用软管浇水；痛打', 1444180885000, 0, 'həʊz', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (59, 'hydrostatic', 'hydrostatic', 'adj. 流体静力学的；静水力学的', 1451808929000, 0, ',haɪdrə(ʊ)''stætɪk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (60, 'mendicant', 'mendicant', 'adj. 行乞的；托钵修道会的
n. 乞丐；托钵僧', 1444094513000, 0, '''mendɪk(ə)nt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (61, '樟树', 'zhangshu', '[林] camphor tree
Camphora officinarum', 1444091860000, 0, 'zhāng shù', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (62, 'villa', 'villa', 'n. 别墅；郊区住宅', 1444179081000, 0, '''vɪlə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (63, 'veteran', 'veteran', 'n. 老兵；老手；富有经验的人；老运动员
adj. 经验丰富的；老兵的', 1444180355000, 0, '''vet(ə)r(ə)n', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (64, 'crapper', 'crapper', 'n. 厕所', 1444178534000, 0, '''kræpə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (65, 'mean value', 'mean value', '[数] 平均值，平均数', 1444094345000, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (66, 'overact', 'overact', 'vt. 将…表演得过火；行动过火
vi. 夸张表演；表演过火', 1444114069000, 0, 'əʊvər''ækt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (67, 'opaque', 'opaque', 'adj. 不透明的；不传热的；迟钝的
n. 不透明物
vt. 使不透明；使不反光', 1444095035000, 0, 'ə(ʊ)''peɪk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (68, 'exact', 'exact', 'adj. 准确的，精密的；精确的
vt. 要求；强求；急需
vi. 勒索钱财', 1446103563000, 0, 'ɪg''zækt; eg-', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (69, 'tuition', 'tuition', 'n. 学费；讲授', 1444179926000, 0, 'tjuː''ɪʃ(ə)n', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (70, 'obscure', 'obscure', 'adj. 模糊的；晦涩的；昏暗的
vt. 遮掩；使变暗；使难理解', 1444113632000, 0, 'əb''skjʊə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (71, 'overall', 'overall', 'adj. 全部的；全体的；一切在内的
adv. 全部地；总的说来
n. 工装裤；罩衫', 1444095430000, 0, '''əʊvərɔːl', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (72, 'oval', 'oval', 'adj. 椭圆的；卵形的
n. 椭圆形；卵形
n. (Oval)人名；(法)奥瓦尔', 1444094670000, 0, '''əʊv(ə)l', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (73, 'timbrel', 'timbrel', 'n. 小手鼓；铃鼓', 1444472550000, 0, '''tɪmbr(ə)l', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (74, 'anthropoid', 'anthropoid', 'adj. 类人猿的；类人的；（猿等）似人类的
n. 类人猿', 1449203343000, 0, '''ænθrəpɒɪd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (75, 'satire', 'satire', 'n. 讽刺；讽刺文学，讽刺作品', 1449204275000, 0, '''sætaɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (76, 'champion', 'champion', 'n. 冠军；拥护者；战士
vt. 支持；拥护
adj. 优胜的；第一流的', 1445922934000, 0, '''tʃæmpɪən', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (77, 'plastics', 'plastics', 'n. 塑料；整形外科；外科修补术', 1444179011000, 0, '''plæstɪks', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (78, 'macadamia', 'macadamia', 'n. [植] 澳洲坚果树；夏威夷果', 1444094238000, 0, ',mækə''deɪmɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (79, 'booby', 'booby', 'n. 呆子；傻瓜；塘鹅；（在比赛或游戏中）成绩最差的人', 1445913427000, 0, '''buːbɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (80, 'wagon', 'wagon', 'n. 货车，四轮马车
vt. 用运货马车运输货物', 1444799905000, 0, '''wæg(ə)n', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (81, 'observational', 'observational', 'adj. 观测的；根据观察的', 1444113870000, 0, ',ɒbzə''veɪʃənəl', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (82, 'acrylic', 'acrylic', 'adj. 丙烯酸的', 1450770510000, 0, 'ə''krɪlɪk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (83, 'oppress', 'oppress', 'vt. 压迫，压抑；使……烦恼；使……感到沉重', 1444094929000, 0, 'ə''pres', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (84, 'outnumber', 'outnumber', 'vt. 数目超过；比…多', 1444094889000, 0, 'aʊt''nʌmbə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (85, 'habitat', 'habitat', 'n. [生态] 栖息地，产地', 1444178303000, 0, '''hæbɪtæt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (86, 'hoarse', 'hoarse', 'adj. 嘶哑的', 1444178193000, 0, 'hɔːs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (87, 'habilitate', 'habilitate', 'vt. 提供周转资金；给…穿着
vi. 取得任职资格', 1444178369000, 0, 'hə''bɪlɪteɪt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (88, 'meridiem', 'meridiem', 'n. 正午', 1444113944000, 0, 'mə''ridi:əm', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (89, 'queer', 'queer', 'adj. 奇怪的；同性恋的；不舒服的；心智不平衡的
vt. 搞糟；使陷于不利地位
n. 同性恋者；怪人；伪造的货币', 1444178740000, 0, 'kwɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (90, 'trolley system', 'trolley system', '空中吊运车系统', 1444114402000, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (91, 'overpass', 'overpass', 'vt. 超越；胜过；忽略
n. 天桥；陆桥', 1444094652000, 0, '''əʊvəpɑːs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (92, 'flange', 'flange', 'n. [机] 法兰；[机][古生] 凸缘；轮缘；边缘
vt. 给…装凸缘', 1450770937000, 0, 'flæn(d)ʒ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (93, 'epode', 'epode', 'n. 长短句交替的古代抒情诗体；希腊抒情颂诗第三节', 1445964115000, 0, '''epəʊd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (94, 'quench', 'quench', 'vt. 熄灭，[机] 淬火；解渴；结束；冷浸
vi. 熄灭；平息', 1444178830000, 0, 'kwen(t)ʃ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (95, 'corrugation', 'corrugation', 'n. 起皱；皱状；灌水沟；波纹成形', 1449203675000, 0, ',kɔːrʊ''ɡeɪʃən', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (96, 'lozenge', 'lozenge', 'n. [数] 菱形；锭剂；菱形窗玻璃', 1445406718000, 0, '''lɒzɪn(d)ʒ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (97, 'cabinetmaker', 'cabinetmaker', 'n. 家具工；细工木匠', 1444212810000, 0, '''kæbɪnɪt,meɪkə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (98, 'overlook', 'overlook', 'vt. 忽略；俯瞰；远眺；检查；高耸于…之上
n. 忽视；眺望', 1444094596000, 0, 'əʊvə''lʊk', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (99, 'hibiscus', 'hibiscus', 'n. 木槿；芙蓉花', 1444091508000, 0, 'hɪ''bɪskəs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (100, 'trial and error', 'trial and error', '反复试验；尝试错误法', 1444114359000, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (101, 'reverence', 'reverence', 'n. 崇敬；尊严；敬礼
vt. 敬畏；尊敬', 1444181927000, 0, '''rev(ə)r(ə)ns', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (102, '玻璃钢', 'boligang', '[材] glass fiber reinforced plastics', 1444179021000, 0, 'bō li gāng', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (103, 'billow', 'billow', 'n. 巨浪
vi. 翻腾
vt. 使翻腾', 1446103149000, 0, '''bɪləʊ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (104, 'dubious', 'dubious', 'adj. 可疑的；暧昧的；无把握的；半信半疑的', 1444114756000, 0, '''djuːbɪəs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (105, 'bestow', 'bestow', 'vt. 使用；授予；放置；留宿', 1448850572000, 0, 'bɪ''stəʊ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (106, 'fleur', 'fleur', 'n. 粉状填料', 1443114699000, 0, '''flɝ də ''li; ''flɝ də ''lis', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (107, 'overhear', 'overhear', 'vt. 无意中听到；偷听
vi. 无意中听到；偷听到', 1444094851000, 0, 'əʊvə''hɪə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (108, 'calcify', 'calcify', 'vi. 钙化；变成石灰质
vt. 使钙化；使思想僵化', 1444212923000, 0, '''kælsɪfaɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (109, 'hum', 'hum', 'vi. 发低哼声
vt. 用哼声表示
n. 嗡嗡声；哼声；杂声
int. 哼；嗯', 1444180501000, 0, 'hʌm', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (110, 'cellar', 'cellar', 'n. 地窖；酒窖；地下室
vt. 把…藏入地窖
n. (Cellar)人名；(捷)采拉尔', 1445913338000, 0, '''selə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (111, 'ova', 'ova', 'n. 卵细胞（ovum的复数）', 1444094689000, 0, '''əʊvə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (112, 'hover', 'hover', 'vi. 盘旋，翱翔；徘徊
n. 徘徊；盘旋；犹豫
vt. 孵；徘徊在…近旁', 1444180036000, 0, '''hɒvə', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (113, 'dullard', 'dullard', 'n. 笨蛋；愚人', 1445913445000, 0, '''dʌləd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (114, 'a.m', 'a#m', 'abbr. （拉）午前；早上（ante meridiem）', 1444114003000, 0, '', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (115, 'obscene', 'obscene', 'adj. 淫秽的；猥亵的；可憎的', 1444113616000, 0, 'əb''siːn', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (116, 'oxide', 'oxide', 'n. [化学] 氧化物', 1444095705000, 0, '''ɒksaɪd', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (117, 'oxid', 'oxid', 'n. 氧化物', 1444095676000, 0, '''ɔksid', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (118, 'treachery', 'treachery', 'n. 背叛；变节；背叛行为', 1444114456000, 0, '''tretʃ(ə)rɪ', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (119, 'cultivate', 'cultivate', 'vt. 培养；陶冶；耕作', 1444179952000, 0, '''kʌltɪveɪt', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (120, 'menace', 'menace', 'n. 威胁；恐吓
vi. 恐吓；进行威胁
vt. 威胁；恐吓', 1444094440000, 0, '''menəs', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (121, 'archive', 'archive', 'n. 档案馆；档案文件
vt. 把…存档', 1447578846000, 0, '''ɑrkaɪv', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (122, 'me too', 'me too', '我也是，我也一样', 1444094317000, 0, 'mi: tu:', '__DEFAULT__', -1, null, 3);
INSERT INTO "main"."notes" VALUES (123, 'docile', 'docile', 'adj. 温顺的，驯服的；容易教的', 1444472577000, 0, '''dəʊsaɪl', '__DEFAULT__', -1, null, 3);

-- ----------------------------
-- Table structure for note_tag_relations
-- ----------------------------
-- DROP TABLE IF EXISTS "main"."note_tag_relations";
CREATE TABLE note_tag_relations (word TEXT,tag TEXT,username TEXT,CONSTRAINT PK_T3 PRIMARY KEY (word , tag , username));

-- ----------------------------
-- Records of note_tag_relations
-- ----------------------------

-- ----------------------------
-- Table structure for practice
-- ----------------------------
-- DROP TABLE IF EXISTS "main"."practice";
CREATE TABLE practice (_id INTEGER PRIMARY KEY,word TEXT,score INTEGER);

-- ----------------------------
-- Records of practice
-- ----------------------------
INSERT INTO "main"."practice" VALUES (1, 'grill', 1568);
INSERT INTO "main"."practice" VALUES (2, 'lawsuit', 1209);
INSERT INTO "main"."practice" VALUES (3, 'dispatcher', 1139);
INSERT INTO "main"."practice" VALUES (4, 'fallout', 859);
INSERT INTO "main"."practice" VALUES (5, 'rally', 746);
INSERT INTO "main"."practice" VALUES (6, 'stock exchange', 1522);
INSERT INTO "main"."practice" VALUES (7, 'attic', 805);

-- ----------------------------
-- Table structure for tags
-- ----------------------------
-- DROP TABLE IF EXISTS "main"."tags";
CREATE TABLE tags (tag TEXT PRIMARY KEY,username TEXT);

-- ----------------------------
-- Records of tags
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
-- DROP TABLE IF EXISTS "main"."user";
CREATE TABLE user (username TEXT PRIMARY KEY,password TEXT,time INTEGER,rem_time TEXT);

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO "main"."user" VALUES ('__DEFAULT__', '', 1464494270868, 0);

-- ----------------------------
-- Indexes structure for table notes
-- ----------------------------
CREATE INDEX "main"."word_table_index"
ON "notes" ("word" ASC);

-- ----------------------------
-- Indexes structure for table note_tag_relations
-- ----------------------------
CREATE INDEX "main"."relation_table_index"
ON "note_tag_relations" ("word" ASC);
CREATE INDEX "main"."word_tag_relation_table_index"
ON "note_tag_relations" ("tag" ASC);
