package com.nakilnat.nakilnat.ui.profile.settings.askedquestions;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nakilnat.nakilnat.R;
import com.nakilnat.nakilnat.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class AskedQuestionsFragment extends BaseFragment {

    RecyclerView recyclerView;

    List<AskedQuestionsList> askedQuestionsLists;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_asked_questions);
        bottomBarSetup(R.id.bottomProfile);
        initTopBarContents("Sık Sorulan Sorular");
        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        AskedQuestionsAdapter askedQuestionsAdapter = new AskedQuestionsAdapter(askedQuestionsLists);
        recyclerView = findViewById(R.id.asked_question_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(askedQuestionsAdapter);
    }

    private void initData() {
        askedQuestionsLists = new ArrayList<>();
        askedQuestionsLists.add(new AskedQuestionsList("Nakilnat Nedir?", "Güvenilir, hızlı ve teknoloji ile uyumlu bir taşımacılık deneyimi yaşamak isteyen bireyler için Nakilnat.com, başta üretici firmalar olmak üzere, tüm işletmelerin karayolu taşımacılık süreçlerini dijitalleştirerek teknolojiyi lojistiğe taşımak için kolları sıvadı. Son olarak bu alanda verdiği hizmetlerini tek bir platformda toplayarak hizmet kalitesini arttırmak için çalışmalarda bulundu.\n" +
                "\n" +
                "Kurulduğu günden bu güne, güvenilir ve üstün hizmet kalitesiyle ön plana çıkan Nakilnat.com, uçtan uca hizmet yaklaşımıyla müşterilerini memnun etmeyi sürdürürken, tecrübesini kanıtlamaya da devam ediyor! Nakliyat sektörünün en son yeniliklerini takip eden ve her geçen gün kendini geliştirmeye devam eden Nakilnat.com, yalnızca taşımacılık hizmeti sağladığı müşterileri için değil, kamyoncular için de birçok kolaylık sunuyor. Onların kazançlarını arttırmaya destek olacak atılımlarıyla sektörün her köşesinde varlığını hissettiriyor.\n" +
                "\n"));
        askedQuestionsLists.add(new AskedQuestionsList("Nakilnat'ın kurucusu kimdir?", "7.9"));
        askedQuestionsLists.add(new AskedQuestionsList("Nakilnat uygulaması ile neler yapılabilir?", "7.9"));
        askedQuestionsLists.add(new AskedQuestionsList("Koray Aydın kimdir?", "7.9"));
        askedQuestionsLists.add(new AskedQuestionsList("Nasıl Nakilnat müşterisi olurum?", "7.9"));
        askedQuestionsLists.add(new AskedQuestionsList("Iron Man", "7.9"));
    }
}
