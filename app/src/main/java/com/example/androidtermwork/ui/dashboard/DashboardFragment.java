package com.example.androidtermwork.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidtermwork.R;
import com.example.androidtermwork.webView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class News {
    public String title;
    public String resource;
    public String title2;
    public String time;
    public String article;
    public String editor;

    public News(String title, String resource, String title2, String time, String article, String editor) {
        this.title = title;
        this.resource = resource;
        this.title2 = title2;
        this.time = time;
        this.article = article;
        this.editor = editor;
    }
}

public class DashboardFragment extends Fragment {
    public static List<News> newLists = Arrays.asList(
            new News("中国共产党第十九届中央委员会第六次全体会议公报",
                    "“学习强国”学习平台",
                    "中国共产党第十九届中央委员会第六次全体会议公报（2021年11月11日中国共产党第十九届中央委员会第六次全体会议通过）",
                    "2021-11-12",
                    "中国共产党第十九届中央委员会第六次全体会议，于2021年11月8日至11日在北京举行。\\n' +\n" +
                            "      '出席这次全会的有，中央委员197人，候补中央委员151人。中央纪律检查委员会常务委员会委员和有关方面负责同志列席会议。\\n' +\n" +
                            "      '全会由中央政治局主持。中央委员会总书记习近平作了重要讲话。\\n' +\n" +
                            "      '全会听取和讨论了习近平受中央政治局委托作的工作报告，审议通过了《中共中央关于党的百年奋斗重大成就和历史经验的决议》，审议通过了《关于召开党的第二十次全国代表大会的决议》。习近平就《中共中央关于党的百年奋斗重大成就和历史经验的决议（讨论稿）》向全会作了说明。\\n' +\n" +
                            "      '全会认为，总结党的百年奋斗重大成就和历史经验，是在建党百年历史条件下开启全面建设社会主义现代化国家新征程、在新时代坚持和发展中国特色社会主义的需要；是增强政治意识、大局意识、核心意识、看齐意识，坚定道路自信、理论自信、制度自信、文化自信，做到坚决维护习近平同志党中央的核心、全党的核心地位，坚决维护党中央权威和集中统一领导，确保全党步调一致向前进的需要；是推进党的自我革命、提高全党斗争本领和应对风险挑战能力、永葆党的生机活力、团结带领全国各族人民为实现中华民族伟大复兴的中国梦而继续奋斗的需要。全党要坚持唯物史观和正确党史观，从党的百年奋斗中看清楚过去我们为什么能够成功、弄明白未来我们怎样才能继续成功，从而更加坚定、更加自觉地践行初心使命，在新时代更好坚持和发展中国特色社会主义。",
                    "秦辰宇"),
            new News("习近平主持召开中央政治局会议 分析研究2022年经济工作", "“学习强国”学习平台",
                    "征求对经济工作的意见和建议\n" +
                            "\n" +
                            "中共中央召开党外人士座谈会\n" +
                            "\n" +
                            "习近平主持并发表重要讲话\n" +
                            "\n" +
                            "李克强通报有关情况 汪洋王沪宁韩正出席",
                    "2021-12-06",
                    "新华社北京12月6日电 12月2日，中共中央在中南海召开党外人士座谈会，就今年经济形势和明年经济工作听取各民主党派中央、全国工商联负责人和无党派人士代表的意见和建议。中共中央总书记习近平主持座谈会并发表重要讲话强调，要全面贯彻落实中共十九届六中全会精神，重温多党合作的历程和作用，发扬光荣传统，坚守合作初心，围绕宏观政策要稳健有效、微观政策要激发市场主体活力、改革开放政策要增强发展动力、社会政策要兜住民生底线，积极履行职能，加强自身建设，引导广大成员和所联系群众把会议精神转化为共同奋斗的政治共识，在全面建设社会主义现代化国家新征程中继续团结奋斗。\n" +
                            "\n" +
                            "座谈会上，民革中央主席万鄂湘、民盟中央主席丁仲礼、民建中央主席郝明金、民进中央主席蔡达峰、农工党中央主席陈竺、致公党中央常务副主席蒋作君、九三学社中央主席武维华、台盟中央主席苏辉、全国工商联主席高云龙、无党派人士代表宇如聪先后发言。他们完全赞同中共中央对当前我国经济形势的分析判断和明年经济工作的谋划考虑，并就加强宏观调控、提升政策效能，改善进出口结构、促进内外贸一体化，提高生产制造装备水平、加快我国高端制造业发展，健全人口服务体系、激发内需增长潜力，完善我国疫情防控策略、为稳增长打牢扎实基础，强化法治保障和政策引领、科学有序推进“双碳”目标，转变涉企财税支持方式、提高财政收入质量，加强农村生态环境治理、夯实乡村振兴基础，加大改革力度、助力推进共同富裕，强化中小企业发展政策导向和可持续发展能力，发挥民企在促进共同富裕中积极作用，完善科技领域“揭榜挂帅”工作机制，加快推动省际交界地区协同发展等提出意见建议。",
                    "张秋兰"),
            new News("习近平主持党外人士座谈会并发表重要讲话", "“学习强国”学习平台",
                    "",
                    "2021-12-06",
                    "", "张玙蕗"),
            new News("国家主席习近平任免驻外大使", "“学习强国”学习平台",
                    "",
                    "2021-12-03",
                    "", "齐翼"),
            new News("习近平向“2021从都国际论坛”开幕式发表视频致辞",
                    "“学习强国”学习平台",
                    "",
                    "2021-12-05",
                    "", "秦辰宇"),
            new News("习近平同老挝人民革命党中央总书记、国家主席通伦共同出席中老铁路通车仪式",
                    "“学习强国”学习平台",
                    "",
                    "2021-12-03",
                    "", "胡佳"),
            new News("习近平向中国－拉共体论坛第三届部长会议发表视频致辞",
                    "“学习强国”学习平台",
                    "",
                    "2021-12-03",
                    "", "齐翼"),
            new News("习近平出席全国宗教工作会议并发表重要讲话",
                    "“学习强国”学习平台",
                    "",
                    "2021-12-04",
                    "", "秦辰宇"));

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        dashboardViewModel =
//                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final ListView listView = root.findViewById(R.id.newsList);
//        dashboardViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//            }
//        });
        updateListView(listView);
        return root;
    }

    public void updateListView(ListView listView) {
        final List<Map<String, Object>> listItem = new ArrayList<>();
        for (int i = 0; i < newLists.size(); i++) {
            Map<String, Object> tempMap = new HashMap<>();
            tempMap.put("title", newLists.get(i).title);
            tempMap.put("resource", newLists.get(i).resource + newLists.get(i).time);
            listItem.add(tempMap);
        }
        SimpleAdapter mAdapter = new SimpleAdapter(getActivity(), listItem, R.layout.news_list_item, new String[]{"title", "resource"}, new int[]{R.id.title, R.id.resource});
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //跳转页面，显示详细信息
                Intent intent = new Intent(getActivity(), webView.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", newLists.get(i).title);
                bundle.putString("resource", newLists.get(i).resource);
                bundle.putString("title2", newLists.get(i).title2);
                bundle.putString("time", newLists.get(i).time);
                bundle.putString("article", newLists.get(i).article);
                bundle.putString("editor", newLists.get(i).editor);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}