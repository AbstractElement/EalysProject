INSERT INTO SKILLS(NAME) VALUES ('Разработка проектной документации');
INSERT INTO SKILLS(NAME) VALUES ('Нормативно-техническая документация');
INSERT INTO SKILLS(NAME) VALUES ('Autodesk Revit');
INSERT INTO SKILLS(NAME) VALUES ('AutoCAD');
INSERT INTO SKILLS(NAME) VALUES ('Пользователь ПК');
INSERT INTO SKILLS(NAME) VALUES ('Проектирование');
INSERT INTO SKILLS(NAME) VALUES ('Разработка чертежей');
INSERT INTO SKILLS(NAME) VALUES ('Lira');
INSERT INTO SKILLS(NAME) VALUES ('VetCad');
INSERT INTO SKILLS(NAME) VALUES ('Разработка разделов КЖ');
INSERT INTO SKILLS(NAME) VALUES ('Разработка разделов КД');

INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, FIRST_NAME, LAST_NAME, ORGANIZATION_NAME, POSITION, PHONE_NUMBER, SEX, AGE, ABOUT, PHOTO, ENABLED) VALUES ('max@gmail.com', '$2a$08$tVE8NUjsdCdfRu5VHiLqr.ovSzucB2uGY2BMCf7MKrCg.J3Rh2JaS', 'maximus', 'Максим',  'Иванцев', 'Инженерно-технический центр ОАО «Газпром трансгаз Беларусь»', 'Инженер-проектировщик ПГС', '+984231534112', 'male', 41, 'Ведущий инженер-проектировщик (ПГС, стаж 14 лет) выполнит разработку разделов КЖ, КМ, КД, АС, КМД зданий и сооружений гражданского и промышленного назначения. Чертежи : AutoCad, SPDS, VetCad, 3ds Max, CorelDRAW и др. Расчёты : SCAD, Lira, Base, Sapfir, Foundation, Мономах и др. А также разработка КМ и КМД несущих и ограждающих металлоконструкций, витражных систем и вентилируемых фасадов (расчёт и конструирование). Опыт работы с ТАТПРОФ, ALUTECH, REALIT, HUECK, ALCOA, АГРИСОВГАЗ (AGS), SCHUCO, ALUMIL, METALPLAST, METRA, U-KON, Металл Профиль, Албес.', 'male.png', 1);
INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, FIRST_NAME, LAST_NAME, ORGANIZATION_NAME, POSITION, PHONE_NUMBER, SEX, AGE, ABOUT, PHOTO, ENABLED) VALUES ('egor@gmail.com', '$2a$08$tVE8NUjsdCdfRu5VHiLqr.ovSzucB2uGY2BMCf7MKrCg.J3Rh2JaS', 'egor2001', 'Егор', 'Черненко', 'НПК "Вектор"', 'Иженер-проектировщик', '+375294356262', 'male', 55, 'Аттестованный Индивидуальный предприниматель готов выполнять разделы КМ, КЖ, КД. Опыт работы 8 лет. Договор.Безнал. Уверенный пользователь ACAD, MS Office.Имею права категории “B” , “C” и личный автомобиль, коммуникабелен, своевременность и качество выполненных работ гарантирую.', 'male.png', 1);
INSERT INTO USERS (EMAIL, PASSWORD, USERNAME, FIRST_NAME, LAST_NAME, ORGANIZATION_NAME, POSITION, PHONE_NUMBER, SEX, AGE, ABOUT, PHOTO, ENABLED) VALUES ('lena@gmail.com', '$2a$08$tVE8NUjsdCdfRu5VHiLqr.ovSzucB2uGY2BMCf7MKrCg.J3Rh2JaS', 'lena1945', 'Елена', 'Касперович', 'ОАО СГЦ Западный', 'Специалист по охране труда', '+375295672622', 'female', 35, 'Специализация - создание проектов новых серий КПД и привязка "старых" серий к новому оборудованию. Знание технологий производства современных домостроительных комбинатов. Творческий подход к работе. Ответственность. Юридические знания. Водительские права. Наличие аттестата гл. специалиста-конструктора. Опыт организации строительства, проектирования, продаж, в т. ч. недвижимости', 'female.png', 1);

INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 1);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 2);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 3);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 4);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 5);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 6);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 7);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (1, 8);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (2, 5);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (2, 10);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (2, 11);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (3, 9);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (2, 1);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (2, 2);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (3, 1);
INSERT INTO SKILLS_USERS (USER_ID, SKILL_ID) VALUES (3, 4);

INSERT INTO ROLES (USER, ROLE) VALUES (1, 'ROLE_USER');
INSERT INTO ROLES (USER, ROLE) VALUES (2, 'ROLE_ADMIN');
INSERT INTO ROLES (USER, ROLE) VALUES (2, 'ROLE_USER');
INSERT INTO ROLES (USER, ROLE) VALUES (3, 'ROLE_USER');

INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('АВТОМАТИЧЕСКАЯ СИСТЕМА ЗАЩИТЫ ПОЕЗДА', current_timestamp(), 'ОБЕСПЕЧИТЬ ПОДАЧУ К PES ЧЕРЕЗ АНТЕННЫ ИНФОРМАЦИИ О БЕЗОПАСНЫХ СКОРОСТЯХ И ПУНКТАХ ОСТАНОВКИ', 'На поезде установлена одна или большее количество антенн, которые получают сигналы с оборудования, установленного на полосе отчуждения, дающего информацию относительно безопасных скоростей или пунктов остановки. Эта информация проходит обработку перед поступлением в программируемую электронную систему. Другой главный вход в программную электронную систему - вход от тахометров или других средств измерения фактической скорости движения поезда. Главный выход программной электронной системы - сигнал на реле безопасности, управляющее чрезвычайным тормозом.');
INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('СИГНАЛЬНАЯ СИСТЕМА', current_timestamp(), 'ПОДАЧА СИГНАЛА ОБЩЕЙ ТРЕВОГИ GPA', 'На изолированной нефтегазовой платформе должны быть предусмотрены эффективные меры для эвакуации и спасения людей в случае инцидентов, потенциально угрожающих жизни. Эти меры должны гарантировать, что персонал будет быстро приведен в готовность при возникновении ситуации, быстро доберется до безопасного места сбора, будет эвакуирован с платформы управляемым способом (на вертолете или спасательной шлюпке) и затем доставлен в безопасное место. Эффективные меры по эвакуации и спасению людей являются существенной частью изолированной системы. ');
INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('СИСТЕМА УПРАВЛЕНИЯ ПЬЕЗОКЛАПАНОМ', current_timestamp(), 'ПЕРЕДАТЬ ОПРЕДЕЛЕННОЕ КОЛИЧЕСТВО ЭЛЕКТРИЧЕСКОГО ЗАРЯДА НА ПЬЕЗОПРИВОД ГОЛОВОК, ЧТОБЫ КЛАПАН ЗАКРЫЛСЯ В ОПРЕДЕЛЕННОЕ ВРЕМЯ', '. Когда на элемент пьезоклапана подают напряжение, он удлиняется. Пьезоклапан под напряжением закрыт, а в противном случае - открыт. Если элемент не теряет заряд, состояние клапана сохраняется. Система управления пьезоклапаном предназначена для распыления огнеопасной и взрывчатой жидкости в реактивном двигателе судна.');
INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('НЕФТЯНОЙ ИСПАРИТЕЛЬ', current_timestamp(), 'ВХОДЫ: НЕФТЯНОЙ ПОТОК, НАГРЕТЫЙ ПЕЧЬЮ ', 'Испаритель нефти состоит из печи, содержащей змеевик и горелку, на которую подается природный газ. Нефть поступает в змеевик, испаряется и покидает катушку в виде перегретого пара. Природный газ в горелке смешивается с внешним воздухом и горит. Газы сгорания выводятся через стек. Нефтяной поток управляется системой, которая включает: клапан управления потоком FCV, элемент потока FE, который измеряет поток нефти; контроллер потока FC и сигнал низкого потока FAL, который включается, если нефтяной поток уменьшается ниже заданного уровня. Поток природного газа проходит через автоматический клапан снижения давления PRV нa главный клапан горелки TCV и клапан PV. Главный клапан управления горелкой приводится в действие температурным контроллером ТС, который получает сигнал от температурного элемента ТЕ, измеряющего температуру нефтяного пара.');
INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('ЛИНИЯ ПЕРЕМЕЩЕНИЯ ВЕЩЕСТВА А ОТ РЕЗЕРВУАРА ДО РЕАКТОРА', current_timestamp(), 'ПРИМЕР ПРОЦЕССА', 'Вещества А и В непрерывно перемещаются при помощи насоса из соответствующих резервуаров в реактор для соединения и формирования продукта С. Предположим, что для того, чтобы избежать опасности взрыва в реакторе, должно быть больше вещества А. При полном описании проекта схема должна была бы включать много не приводимых здесь деталей (воздействие давления, температуры, колебаний, время реакции, совместимость насосов и т.д.).');
INSERT INTO PROJECTS (NAME, DATE, TARGET, DESCRIPTION) VALUES ('ПРОЦЕДУРЫ', current_timestamp(), 'НЕБОЛЬШОЕ СЕРИЙНОЕ ПРОИЗВОДСТВО КОМПОНЕНТА X', 'Рассмотрим небольшое серийное производство по изготовлению безопасных пластмассовых компонент, которые должны соответствовать техническим условиям на них как по свойствам материала, так и по цвету.');

INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Отказ передатчика', 2, 'Рассмотрены в отдельном исследовании HAZOP оборудования полосы отчуждения', 1, 6);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Передатчик установлен слишком близко к рельсу', 1,  'Может быть повреждено оборудование', 1, 2);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Передатчик установлен слишком далеко от рельса', 1, 'Сигнал может быть не принят', 1, 9);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Получение сигнала со смежной дорожки', 3, 'На процессор передано неправильное значение', 1, 4);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Антенна касается рельса под напряжением', 2, 'Антенна и другое оборудование попадают под напряжение', 1, 6);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Прием сигналов со смежного кабеля', 1,  'Неправильный сигнал может привести к неверным действиям системы', 1, 2);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Внезапная блокировка колес', 3, 'На тахометре может быть показание нулевой скорости движения', 1, 9);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Внезапный запуск блокированных колес дает неверный сигнал', 3, 'Тахометр показывает неправильную скорость движения', 1, 4);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Внезапные изменения в выходе, вызванные вращением колеса', 2, 'Может вызвать действие, основанное на неправильной скорости движения', 1, 6);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Тахометр блокирован', 2,  'На тахометре может быть показание нулевой скорости движения', 1, 2);
INSERT INTO RISKS (NAME, AUTHOR, DESCRIPTION, PROJECT_ID, DANGER_LEVEL) VALUES ('Наложены другие сигналы', 1, 'На тахометре может быть показание нулевой скорости движения', 1, 9);

INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('photo2.gif', 1);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('test2.gif', 1);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('test3.jpg', 1);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('test4.jpg', 1);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('test1.jpg', 2);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('photo1.jpg', 3);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('photo3.gif', 4);
INSERT INTO IMAGES (IMAGE_NAME, PROJECT_ID) VALUES ('photo4.png', 5);

INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (1, 1, 'OWNER');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (2, 1, 'GUEST');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (3, 1, 'GUEST');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (1, 4, 'OWNER');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (1, 5, 'OWNER');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (1, 6, 'OWNER');
INSERT INTO ROLE_USER_ON_PROJECT (USER, PROJECT_ID, ROLE_ON_PROJECT) VALUES (1, 3, 'OWNER');
