package bg.softuni.onlinepharmacynews.init;


import bg.softuni.onlinepharmacynews.model.entity.News;
import bg.softuni.onlinepharmacynews.repository.NewsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class InitializeData implements CommandLineRunner {
    private final NewsRepository newsRepository;

    public InitializeData(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if (newsRepository.count() == 0) {
            List<News> newsList = new ArrayList<>();

            News news1 = new News();
            news1.setTitleEn("New Technological Advances in 2024");
            news1.setTitleBg("Нови технологични постижения през 2024 година");
            news1.setContentEn("The year 2024 has seen a multitude of advancements in technology. From breakthroughs in artificial intelligence to new innovations in renewable energy, the technological landscape is rapidly evolving. Companies across the globe are investing heavily in research and development to bring these new technologies to market. This article explores some of the most significant technological advancements of the year, highlighting their potential impact on various industries and everyday life.");
            news1.setContentBg("През 2024 година се наблюдават множество постижения в технологиите. От пробиви в изкуствения интелект до нови иновации в областта на възобновяемата енергия, технологичният пейзаж се развива бързо. Компании по целия свят инвестират сериозно в научноизследователска и развойна дейност, за да представят тези нови технологии на пазара. Тази статия разглежда някои от най-значимите технологични постижения на годината, като подчертава тяхното потенциално въздействие върху различни индустрии и ежедневието.");
            newsList.add(news1);

            News news2 = new News();
            news2.setTitleEn("Healthcare Innovations: The Future of Medicine");
            news2.setTitleBg("Иновации в здравеопазването: Бъдещето на медицината");
            news2.setContentEn("Innovations in healthcare are transforming the way we approach medicine and patient care. From telemedicine to personalized treatments, these advancements are making healthcare more accessible and effective. Telemedicine has allowed patients to consult with doctors remotely, reducing the need for in-person visits. Personalized medicine, on the other hand, tailors treatments to individual patients based on their genetic makeup, improving the efficacy of treatments and reducing side effects.");
            news2.setContentBg("Иновациите в здравеопазването променят начина, по който подхождаме към медицината и грижата за пациентите. От телемедицината до персонализираните лечения, тези постижения правят здравеопазването по-достъпно и ефективно. Телемедицината позволи на пациентите да се консултират с лекари от разстояние, намалявайки нуждата от присъствени посещения. Персонализираната медицина, от своя страна, адаптира леченията към индивидуалните пациенти въз основа на тяхната генетична структура, като подобрява ефективността на леченията и намалява страничните ефекти.");
            newsList.add(news2);

            News news3 = new News();
            news3.setTitleEn("Global Economic Trends in 2024");
            news3.setTitleBg("Глобални икономически тенденции през 2024 година");
            news3.setContentEn("The global economy in 2024 is characterized by several key trends. Economic growth is being driven by advancements in technology, increased consumer spending, and strong performance in emerging markets. However, there are also challenges, including geopolitical tensions and environmental concerns. This article provides an overview of the major economic trends of the year and their implications for businesses and consumers worldwide.");
            news3.setContentBg("Глобалната икономика през 2024 година се характеризира с няколко ключови тенденции. Икономическият растеж се движи от постиженията в технологиите, увеличените потребителски разходи и силното представяне на развиващите се пазари. Въпреки това има и предизвикателства, включително геополитически напрежения и екологични проблеми. Тази статия предоставя преглед на основните икономически тенденции на годината и техните последствия за бизнеса и потребителите по света.");
            newsList.add(news3);

            News news4 = new News();
            news4.setTitleEn("Climate Change: Impact and Solutions");
            news4.setTitleBg("Климатични промени: въздействие и решения");
            news4.setContentEn("Climate change continues to be one of the most pressing issues of our time. The effects of global warming are being felt worldwide, from rising sea levels to extreme weather events. Scientists and policymakers are working together to develop solutions to mitigate these impacts. This article examines the current state of climate change, its potential effects on the environment and society, and the steps being taken to address this critical issue.");
            news4.setContentBg("Климатичните промени продължават да бъдат едно от най-належащите предизвикателства на нашето време. Ефектите от глобалното затопляне се усещат в цял свят, от покачващите се нива на морето до екстремни метеорологични събития. Учените и политиците работят заедно, за да разработят решения за смекчаване на тези въздействия. Тази статия разглежда текущото състояние на климатичните промени, потенциалните им ефекти върху околната среда и обществото и стъпките, които се предприемат за решаване на този критичен въпрос.");
            newsList.add(news4);

            News news5 = new News();
            news5.setTitleEn("Breakthroughs in Renewable Energy");
            news5.setTitleBg("Пробиви в областта на възобновяемата енергия");
            news5.setContentEn("Renewable energy sources are becoming increasingly important as the world seeks to reduce its reliance on fossil fuels. Recent advancements in solar, wind, and hydroelectric power are making renewable energy more efficient and affordable. This article explores the latest breakthroughs in renewable energy technology and their potential to transform the global energy landscape.");
            news5.setContentBg("Източниците на възобновяема енергия стават все по-важни, тъй като светът се стреми да намали зависимостта си от изкопаемите горива. Последните постижения в слънчевата, вятърната и хидроелектрическата енергия правят възобновяемата енергия по-ефективна и достъпна. Тази статия разглежда най-новите пробиви в технологиите за възобновяема енергия и техния потенциал за трансформиране на глобалния енергиен пейзаж.");
            newsList.add(news5);

            News news6 = new News();
            news6.setTitleEn("Advancements in Artificial Intelligence");
            news6.setTitleBg("Напредък в изкуствения интелект");
            news6.setContentEn("Artificial intelligence (AI) is rapidly changing the way we live and work. From self-driving cars to intelligent personal assistants, AI technologies are becoming more integrated into our daily lives. This article examines the latest advancements in AI and their potential impact on various industries, including healthcare, finance, and transportation.");
            news6.setContentBg("Изкуственият интелект (AI) бързо променя начина, по който живеем и работим. От самоуправляващи се автомобили до интелигентни лични асистенти, AI технологиите стават все по-интегрирани в нашето ежедневие. Тази статия разглежда последните постижения в областта на AI и техния потенциален въздействие върху различни индустрии, включително здравеопазване, финанси и транспорт.");
            newsList.add(news6);

            News news7 = new News();
            news7.setTitleEn("The Future of Space Exploration");
            news7.setTitleBg("Бъдещето на изследването на космоса");
            news7.setContentEn("Space exploration is entering a new era with advancements in technology and increased interest from private companies. Missions to Mars, lunar bases, and space tourism are becoming more feasible. This article discusses the future of space exploration and the exciting possibilities that lie ahead.");
            news7.setContentBg("Изследването на космоса навлиза в нова ера с постиженията в технологиите и нарастващия интерес от частни компании. Мисиите към Марс, лунните бази и космическият туризъм стават все по-осъществими. Тази статия обсъжда бъдещето на изследването на космоса и вълнуващите възможности, които предстоят.");
            newsList.add(news7);

            News news8 = new News();
            news8.setTitleEn("Economic Recovery Post-Pandemic");
            news8.setTitleBg("Икономическо възстановяване след пандемията");
            news8.setContentEn("The global economy is gradually recovering from the impact of the COVID-19 pandemic. Governments and businesses are implementing strategies to rebuild and grow in the post-pandemic world. This article explores the steps being taken to achieve economic recovery and the challenges that remain.");
            news8.setContentBg("Глобалната икономика постепенно се възстановява от въздействието на пандемията от COVID-19. Правителствата и бизнесите прилагат стратегии за възстановяване и растеж в постпандемичния свят. Тази статия разглежда стъпките, които се предприемат за постигане на икономическо възстановяване и предизвикателствата, които остават.");
            newsList.add(news8);

            News news9 = new News();
            news9.setTitleEn("Technological Innovations in Education");
            news9.setTitleBg("Технологични иновации в образованието");
            news9.setContentEn("Technology is transforming education, making learning more accessible and engaging. From online courses to interactive learning tools, innovations are enhancing the educational experience. This article examines the latest technological advancements in education and their potential to shape the future of learning.");
            news9.setContentBg("Технологиите трансформират образованието, като правят ученето по-достъпно и ангажиращо. От онлайн курсове до интерактивни учебни инструменти, иновациите подобряват образователния опит. Тази статия разглежда последните технологични постижения в областта на образованието и техния потенциал за оформяне на бъдещето на ученето.");
            newsList.add(news9);

            News news10 = new News();
            news10.setTitleEn("The Rise of Electric Vehicles");
            news10.setTitleBg("Възходът на електрическите превозни средства");
            news10.setContentEn("Electric vehicles (EVs) are gaining popularity as consumers seek environmentally friendly alternatives to traditional gasoline-powered cars. Advances in battery technology and charging infrastructure are making EVs more practical and accessible. This article explores the growth of the electric vehicle market and the benefits and challenges associated with this shift.");
            news10.setContentBg("Електрическите превозни средства (EVs) набират популярност, тъй като потребителите търсят екологично чисти алтернативи на традиционните бензинови автомобили. Напредъкът в технологиите на батериите и инфраструктурата за зареждане прави EVs по-практични и достъпни. Тази статия разглежда растежа на пазара на електрически превозни средства и ползите и предизвикателствата, свързани с този преход.");
            newsList.add(news10);

            News news11 = new News();
            news11.setTitleEn("Advances in Genetic Research");
            news11.setTitleBg("Напредък в генетичните изследвания");
            news11.setContentEn("Genetic research is uncovering new insights into human health and disease. Advances in genome sequencing and gene editing are paving the way for personalized medicine and new treatments for genetic disorders. This article examines the latest developments in genetic research and their potential to revolutionize healthcare.");
            news11.setContentBg("Генетичните изследвания разкриват нови прозрения за човешкото здраве и болести. Напредъкът в геномното секвениране и редактирането на гени проправя пътя за персонализираната медицина и нови лечения за генетични заболявания. Тази статия разглежда последните разработки в областта на генетичните изследвания и техния потенциал за революциониране на здравеопазването.");
            newsList.add(news11);

            News news12 = new News();
            news12.setTitleEn("The Impact of Social Media on Society");
            news12.setTitleBg("Въздействието на социалните медии върху обществото");
            news12.setContentEn("Social media has transformed the way we communicate and interact with one another. While it offers numerous benefits, such as connecting people across the globe and providing a platform for self-expression, it also poses challenges, including privacy concerns and the spread of misinformation. This article explores the impact of social media on society and the ways in which it is shaping our world.");
            news12.setContentBg("Социалните медии трансформираха начина, по който общуваме и взаимодействаме един с друг. Докато те предлагат множество ползи, като свързване на хора по целия свят и предоставяне на платформа за самоизразяване, те също така поставят предизвикателства, включително проблеми със защитата на личните данни и разпространението на дезинформация. Тази статия разглежда въздействието на социалните медии върху обществото и начините, по които те оформят нашия свят.");
            newsList.add(news12);

            News news13 = new News();
            news13.setTitleEn("Breakthroughs in Cancer Research");
            news13.setTitleBg("Пробиви в изследванията на рака");
            news13.setContentEn("Cancer research has made significant strides in recent years, leading to new treatments and improved survival rates. Advances in immunotherapy, targeted therapies, and early detection methods are offering hope to patients and their families. This article examines the latest breakthroughs in cancer research and their potential to change the landscape of cancer treatment.");
            news13.setContentBg("Изследванията на рака са направили значителни постижения през последните години, водещи до нови лечения и подобрени проценти на оцеляване. Напредъкът в имунотерапията, целевите терапии и методите за ранно откриване предлага надежда на пациентите и техните семейства. Тази статия разглежда последните пробиви в изследванията на рака и техния потенциал да променят пейзажа на лечението на рака.");
            newsList.add(news13);

            News news14 = new News();
            news14.setTitleEn("The Future of Work: Remote and Hybrid Models");
            news14.setTitleBg("Бъдещето на работата: дистанционни и хибридни модели");
            news14.setContentEn("The COVID-19 pandemic has accelerated the adoption of remote and hybrid work models. As businesses adapt to this new reality, they are discovering the benefits and challenges of these arrangements. This article explores the future of work, examining the impact of remote and hybrid models on productivity, employee satisfaction, and workplace dynamics.");
            news14.setContentBg("Пандемията от COVID-19 ускори приемането на дистанционни и хибридни модели на работа. Докато бизнесите се адаптират към тази нова реалност, те откриват ползите и предизвикателствата на тези договорености. Тази статия разглежда бъдещето на работата, като изследва въздействието на дистанционните и хибридните модели върху продуктивността, удовлетвореността на служителите и динамиката на работното място.");
            newsList.add(news14);

            News news15 = new News();
            news15.setTitleEn("The Role of Blockchain in Modern Finance");
            news15.setTitleBg("Ролята на блокчейн в съвременните финанси");
            news15.setContentEn("Blockchain technology is revolutionizing the finance industry by providing a secure and transparent way to conduct transactions. From cryptocurrencies to smart contracts, blockchain is enabling new financial models and reducing the risk of fraud. This article examines the role of blockchain in modern finance and its potential to reshape the industry.");
            news15.setContentBg("Блокчейн технологията революционизира финансовата индустрия, като предоставя сигурен и прозрачен начин за извършване на транзакции. От криптовалути до интелигентни договори, блокчейн позволява нови финансови модели и намалява риска от измами. Тази статия разглежда ролята на блокчейн в съвременните финанси и неговия потенциал да преобрази индустрията.");
            newsList.add(news15);

            News news16 = new News();
            news16.setTitleEn("The Evolution of E-Commerce");
            news16.setTitleBg("Еволюцията на електронната търговия");
            news16.setContentEn("E-commerce has experienced rapid growth over the past decade, transforming the way consumers shop and businesses operate. Advances in technology, such as mobile commerce and artificial intelligence, are driving this evolution. This article explores the latest trends in e-commerce and their impact on the retail landscape.");
            news16.setContentBg("Електронната търговия е преживяла бърз растеж през последното десетилетие, като трансформира начина, по който потребителите пазаруват и бизнесите работят. Напредъкът в технологиите, като мобилната търговия и изкуствения интелект, води тази еволюция. Тази статия разглежда последните тенденции в електронната търговия и тяхното въздействие върху търговския пейзаж.");
            newsList.add(news16);

            News news17 = new News();
            news17.setTitleEn("Innovations in Renewable Energy");
            news17.setTitleBg("Иновации във възобновяемата енергия");
            news17.setContentEn("Renewable energy sources are gaining traction as the world shifts towards more sustainable energy solutions. Innovations in solar, wind, and hydroelectric power are making these sources more viable and cost-effective. This article examines the latest advancements in renewable energy technology and their potential impact on the environment and economy.");
            news17.setContentBg("Източниците на възобновяема енергия набират популярност, тъй като светът се насочва към по-устойчиви енергийни решения. Иновациите в слънчевата, вятърната и хидроелектрическата енергия правят тези източници по-жизнеспособни и икономически ефективни. Тази статия разглежда последните постижения в областта на възобновяемата енергия и техния потенциален въздействие върху околната среда и икономиката.");
            newsList.add(news17);

            News news18 = new News();
            news18.setTitleEn("Advancements in Biotechnology");
            news18.setTitleBg("Постижения в биотехнологиите");
            news18.setContentEn("Biotechnology is advancing at a rapid pace, leading to new medical treatments and innovations in agriculture. From gene editing to biopharmaceuticals, these advancements are improving health outcomes and food security. This article explores the latest developments in biotechnology and their potential to transform various industries.");
            news18.setContentBg("Биотехнологиите напредват с бързи темпове, водейки до нови медицински лечения и иновации в земеделието. От редактиране на гени до биофармацевтици, тези постижения подобряват здравните резултати и хранителната сигурност. Тази статия разглежда последните разработки в областта на биотехнологиите и техния потенциал за трансформиране на различни индустрии.");
            newsList.add(news18);

            News news19 = new News();
            news19.setTitleEn("The Impact of 5G Technology");
            news19.setTitleBg("Въздействието на 5G технологията");
            news19.setContentEn("5G technology is set to revolutionize the way we connect and communicate. With faster speeds and lower latency, 5G will enable new applications and services, from autonomous vehicles to smart cities. This article examines the potential impact of 5G technology on various sectors and its implications for the future.");
            news19.setContentBg("5G технологията е на път да революционизира начина, по който се свързваме и комуникираме. С по-високи скорости и по-ниска латентност, 5G ще позволи нови приложения и услуги, от автономни превозни средства до умни градове. Тази статия разглежда потенциалното въздействие на 5G технологията върху различни сектори и нейните последици за бъдещето.");
            newsList.add(news19);

            News news20 = new News();
            news20.setTitleEn("The Future of Artificial Intelligence");
            news20.setTitleBg("Бъдещето на изкуствения интелект");
            news20.setContentEn("Artificial intelligence (AI) is poised to transform numerous industries, from healthcare to finance. As AI technology advances, it will enable new applications and improve efficiency in various fields. This article explores the future of AI and its potential to reshape our world.");
            news20.setContentBg("Изкуственият интелект (AI) е на път да трансформира множество индустрии, от здравеопазването до финансите. С напредъка на AI технологията, тя ще позволи нови приложения и ще подобри ефективността в различни области. Тази статия разглежда бъдещето на AI и неговия потенциал да преобрази нашия свят.");
            newsList.add(news20);

            News news21 = new News();
            news21.setTitleEn("Sustainable Agriculture Practices");
            news21.setTitleBg("Устойчиви земеделски практики");
            news21.setContentEn("Sustainable agriculture practices are essential for ensuring food security and protecting the environment. Techniques such as crop rotation, conservation tillage, and integrated pest management are helping farmers produce food more sustainably. This article examines the importance of sustainable agriculture and the benefits it offers.");
            news21.setContentBg("Устойчивите земеделски практики са от съществено значение за осигуряване на хранителна сигурност и опазване на околната среда. Техники като ротация на културите, консервационно обработване на почвата и интегрирано управление на вредителите помагат на фермерите да произвеждат храна по-устойчиво. Тази статия разглежда значението на устойчивото земеделие и ползите, които то предлага.");
            newsList.add(news21);

            News news22 = new News();
            news22.setTitleEn("Innovations in Financial Technology (FinTech)");
            news22.setTitleBg("Иновации във финансовите технологии (FinTech)");
            news22.setContentEn("Financial technology, or FinTech, is transforming the financial industry by offering innovative solutions for payments, lending, and investment. From mobile banking apps to blockchain-based systems, FinTech is making financial services more accessible and efficient. This article explores the latest innovations in FinTech and their impact on the industry.");
            news22.setContentBg("Финансовите технологии, или FinTech, трансформират финансовата индустрия, като предлагат иновативни решения за плащания, кредитиране и инвестиции. От мобилни банкови приложения до блокчейн-базирани системи, FinTech прави финансовите услуги по-достъпни и ефективни. Тази статия разглежда последните иновации във FinTech и тяхното въздействие върху индустрията.");
            newsList.add(news22);

            News news23 = new News();
            news23.setTitleEn("The Impact of Climate Change on Global Health");
            news23.setTitleBg("Въздействието на климатичните промени върху глобалното здраве");
            news23.setContentEn("Climate change is having a profound impact on global health, contributing to the spread of diseases, food and water insecurity, and extreme weather events. Addressing climate change is critical for protecting public health. This article examines the links between climate change and health and the steps being taken to mitigate its effects.");
            news23.setContentBg("Климатичните промени оказват дълбоко въздействие върху глобалното здраве, като допринасят за разпространението на болести, несигурността на храната и водата и екстремните метеорологични събития. Адресирането на климатичните промени е от съществено значение за опазването на общественото здраве. Тази статия разглежда връзките между климатичните промени и здравето и стъпките, които се предприемат за смекчаване на техните ефекти.");
            newsList.add(news23);

            News news24 = new News();
            news24.setTitleEn("Advancements in Telemedicine");
            news24.setTitleBg("Постижения в телемедицината");
            news24.setContentEn("Telemedicine is revolutionizing healthcare by allowing patients to receive medical care remotely. Advances in telemedicine technology are improving access to healthcare services, particularly in rural and underserved areas. This article explores the benefits of telemedicine and the latest advancements in this field.");
            news24.setContentBg("Телемедицината революционизира здравеопазването, като позволява на пациентите да получават медицинска помощ от разстояние. Постиженията в телемедицинските технологии подобряват достъпа до здравни услуги, особено в селските и недостъпните райони. Тази статия разглежда ползите от телемедицината и последните постижения в тази област.");
            newsList.add(news24);

            News news25 = new News();
            news25.setTitleEn("The Role of Renewable Energy in Combating Climate Change");
            news25.setTitleBg("Ролята на възобновяемата енергия в борбата с климатичните промени");
            news25.setContentEn("Renewable energy sources, such as solar, wind, and hydroelectric power, are crucial for reducing greenhouse gas emissions and combating climate change. This article examines the role of renewable energy in mitigating climate change and the benefits it offers for the environment and economy.");
            news25.setContentBg("Източниците на възобновяема енергия, като слънчева, вятърна и хидроелектрическа енергия, са от съществено значение за намаляване на емисиите на парникови газове и борбата с климатичните промени. Тази статия разглежда ролята на възобновяемата енергия в смекчаването на климатичните промени и ползите, които тя предлага за околната среда и икономиката.");
            newsList.add(news25);

            News news26 = new News();
            news26.setTitleEn("Innovations in Urban Planning");
            news26.setTitleBg("Иновации в градското планиране");
            news26.setContentEn("Urban planning is evolving to address the challenges of growing populations, climate change, and sustainability. Innovations in smart city technology, green infrastructure, and public transportation are helping to create more livable and resilient cities. This article explores the latest trends in urban planning and their potential to improve urban life.");
            news26.setContentBg("Градското планиране се развива, за да отговори на предизвикателствата на нарастващото население, климатичните промени и устойчивостта. Иновациите в технологиите за умни градове, зелената инфраструктура и обществената транспортна система помагат да се създадат по-живи и устойчиви градове. Тази статия разглежда последните тенденции в градското планиране и техния потенциал за подобряване на градския живот.");
            newsList.add(news26);

            News news27 = new News();
            news27.setTitleEn("The Future of Renewable Energy");
            news27.setTitleBg("Бъдещето на възобновяемата енергия");
            news27.setContentEn("Renewable energy sources, such as solar, wind, and hydroelectric power, are poised to play a significant role in the global energy landscape. Advances in technology and increased investment are making renewable energy more viable and cost-effective. This article explores the future of renewable energy and its potential to transform the way we generate and consume power.");
            news27.setContentBg("Източниците на възобновяема енергия, като слънчева, вятърна и хидроелектрическа енергия, са на път да играят значителна роля в глобалния енергиен пейзаж. Напредъкът в технологиите и увеличените инвестиции правят възобновяемата енергия по-жизнеспособна и икономически ефективна. Тази статия разглежда бъдещето на възобновяемата енергия и нейния потенциал да трансформира начина, по който генерираме и консумираме енергия.");
            newsList.add(news27);

            News news28 = new News();
            news28.setTitleEn("Innovations in Medical Technology");
            news28.setTitleBg("Иновации в медицинските технологии");
            news28.setContentEn("Medical technology is advancing rapidly, leading to new treatments and improved patient outcomes. Innovations in diagnostic tools, medical devices, and telemedicine are transforming healthcare. This article examines the latest advancements in medical technology and their potential to improve patient care.");
            news28.setContentBg("Медицинските технологии напредват бързо, водейки до нови лечения и подобрени резултати за пациентите. Иновациите в диагностичните инструменти, медицинските устройства и телемедицината трансформират здравеопазването. Тази статия разглежда последните постижения в медицинските технологии и техния потенциал за подобряване на грижата за пациентите.");
            newsList.add(news28);

            for (News news : newsList) {
                newsRepository.save(news);
            }


        }
    }
}
