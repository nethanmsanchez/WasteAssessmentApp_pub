package com.example.wasteassessment2;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class Frag_input_Activity extends AppCompatActivity implements OtherFrag.OnOtherFragClickListener, TrashFrag.OnTrashFragClickListener, CompostFrag.OnCompostFragClickListener, RecycleFrag.OnRecycleFragClickListener, InputFrag.OnFragClickListener, StreamsFrag.OnStreamFragClickListener{
    private int currPrompt;
    private String currEntry;
    private String currStream;
    //private String bins;
    private WasteAudit audit;
    private String TAG = "DEbug";
    private List<Double> weights;
    private List<Double> counts;
    private List<Double> volumes;
    private Bin bin;
    private WasteStream recyclables;
    private WasteStream trash;
    private WasteStream compost;
    private WasteStream other;
    private int rec_prog;
    private int tra_prog;
    private int com_prog;
    private int oth_prog;
    private int tot_prog;
    private boolean[] recycledone;
    private boolean[] trashdone;
    private boolean[] compostdone;
    private boolean[] otherdone;



    @Override
    public void onCreate(Bundle savedInstanceState){
        currPrompt = 0;
        //currEntry = new ArrayList<>();
        weights = new ArrayList<>();
        counts = new ArrayList<>();
        volumes = new ArrayList<>();
        currEntry = null;
        currStream = null;
        recyclables = createRecycleStream();
        trash = new WasteStream("trash");
        compost = new WasteStream("compost");
        other = new WasteStream("other");
        bin = new Bin();
        recycledone = new boolean[] {false, false, false, false, false};
        trashdone = new boolean[] {false, false, false, false, false};
        compostdone = new boolean[]{false, false, false, false, false};
        otherdone = new boolean[] {false, false, false, false};


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraginput);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.lightBlue));

        // Check that activity is using layout with FrameLayout
        if(findViewById(R.id.frag_container) != null) {
            // If being restored from a previous state, return so that
            // we don't get overlapping fragments
            if (savedInstanceState != null) {
                return;
            }

            // test string for new frag sequence
            //List<String> string = new ArrayList<>();
            //initPromptsBinBasedAudit(this.prompts);

            // create new fragment
            Log.i(TAG, "Step 1, about to call newInstance");
            //InputFrag testFrag = InputFrag.newInstance(prompts.get(0), currPrompt);
            StreamsFrag startFrag = new StreamsFrag();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frag_container, startFrag).commit();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment){
        if(fragment instanceof InputFrag){
            InputFrag frag = (InputFrag) fragment;
            frag.setOnFragClickListener(this);
        }
        if(fragment instanceof StreamsFrag){
            StreamsFrag frag = (StreamsFrag) fragment;
            frag.setOnStreamFragClickListener(this);
        }
        if(fragment instanceof  RecycleFrag){
            RecycleFrag frag = (RecycleFrag) fragment;
            frag.setOnRecycleFragClickListener(this);
        }
        if(fragment instanceof  TrashFrag){
            TrashFrag frag = (TrashFrag) fragment;
            frag.setOnTrashFragClickListener(this);
        }
        if(fragment instanceof  CompostFrag){
            CompostFrag frag = (CompostFrag) fragment;
            frag.setOnCompostFragClickListener(this);
        }
        if(fragment instanceof  OtherFrag){
            OtherFrag frag = (OtherFrag) fragment;
            frag.setOnOtherFragClickListener(this);
        }
    }

    public void onBackFragClick(String s){
        ProgressBar pb;
        this.currEntry = s;
        if (s.equals("clean paper and cardboard")){
            if(recycledone[0] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("cans")){
            if(recycledone[1] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("plastic bottles and jugs")){
            if(recycledone[2] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("glass bottles")){
            if(recycledone[3] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("other2")){
            if(recycledone[4] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("plastic bags")){
            if(trashdone[0] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("wrappers")){
            if(trashdone[1] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("non-compostable cups")){
            if(trashdone[2] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("plastic containers")){
            if(trashdone[3] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("other3")){
            if(trashdone[4] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("soiled paper and cardboard")){
            if(compostdone[0] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("compostable cups")){
            if(compostdone[1] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("compostable clamshells")){
            if(compostdone[2] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("organics")){
            if(compostdone[3] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("other4")){
            if(compostdone[4] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("hazardous")){
            if(otherdone[0] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("batteries")){
            if(otherdone[1] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("ewaste")){
            if(otherdone[2] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        if(s.equals("wood")){
            if(otherdone[3] == false) {
                pb = findViewById(R.id.progbar);
                pb.incrementProgressBy(-1);
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    public void onForwardFragClick(double c, double w, double v, String s){
        this.currEntry = s;
        this.weights.add(w);
        this.counts.add(c);
        this.volumes.add(v);
        Entry e = new Entry(c, w, v);
        if ("cleanPAC".equals(s)){
            recyclables.addWasteType("clean paper and cardboard", e);
            rec_prog++;
            recycledone[0] = true;
        }
        if(s.equals("cans")){
            recyclables.addWasteType("cans", e);
            rec_prog++;
            recycledone[1] = true;
        }
        if(s.equals("plastic bottles and jugs")){
            recyclables.addWasteType("plastic bottles and jugs", e);
            rec_prog++;
            recycledone[2] = true;
        }
        if(s.equals("glass bottles")){
            recyclables.addWasteType("glass bottles", e);
            rec_prog++;
            recycledone[3] = true;
        }
        if(s.equals("other2")){
            recyclables.addWasteType("other", e);
            rec_prog++;
            recycledone[4] = true;
        }
        if(s.equals("plastic bags")){
            trash.addWasteType("plastic bags", e);
            tra_prog++;
            trashdone[0] = true;
        }
        if(s.equals("wrappers")){
            trash.addWasteType("wrappers", e);
            tra_prog++;
            trashdone[1] = true;
        }
        if(s.equals("non-compostable cups")){
            trash.addWasteType("non-compostable cups", e);
            tra_prog++;
            trashdone[2] = true;
        }
        if(s.equals("plastic containers")){
            trash.addWasteType("plastic containers", e);
            tra_prog++;
            trashdone[3] = true;
        }
        if(s.equals("other3")){
            trash.addWasteType("other", e);
            tra_prog++;
            trashdone[4] = true;
        }
        if(s.equals("soiled paper and cardboard")){
            compost.addWasteType("soiled paper and cardboard", e);
            com_prog++;
            compostdone[0] = true;
        }
        if(s.equals("compostable cups")){
            compost.addWasteType("compostable cups", e);
            com_prog++;
            compostdone[1] = true;
        }
        if(s.equals("compostable clamshells")){
            compost.addWasteType("compostable clamshells", e);
            com_prog++;
            compostdone[2] = true;
        }
        if(s.equals("organics")){
            compost.addWasteType("organics", e);
            com_prog++;
            compostdone[3] = true;
        }
        if(s.equals("other4")){
            compost.addWasteType("other4", e);
            com_prog++;
            compostdone[4] = true;
        }
        if(s.equals("hazardous")){
            other.addWasteType("hazardous", e);
            oth_prog++;
            otherdone[0] = true;
        }
        if(s.equals("batteries")){
            other.addWasteType("batteries", e);
            oth_prog++;
            otherdone[1] = true;
        }
        if(s.equals("ewaste")){
            other.addWasteType("ewaste", e);
            oth_prog++;
            otherdone[2] = true;
        }
        if(s.equals("wood")){
            other.addWasteType("wood", e);
            oth_prog++;
            otherdone[3] = true;
        }

        //ProgressBar pb = findViewById(R.id.progbar);
        //pb.incrementProgressBy(1);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        //nextPrompt(this.prompts);
    }



    private void prevPrompt(List<String> prompts){
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(-1);
        //this.currPrompt--
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    private void initPromptsBinBasedAudit(List<String> list){
        String[] s = new String[100];
        s[0] = "Your are starting your bin based audit, first we'll start with Recyclables." +
                " Please enter the total weight of Clean Paper and Cardboard.";
        s[1] = "Please enter the total weight of Cans.";
        s[2] = "Please enter the total weight of Glass bottles.";
        s[3] = "Please enter the total weight of Plastic bottles and Jugs.";
        s[4] = "Please enter the total weight of Other recyclables.";
        s[5] = "Now we're going to start Trash. Please enter the total weight of Plastic bags.";
        s[6] = "Please enter the total weight of Wrappers.";
        s[7] = "Please enter the total weight of Non-recyclable or compostable cups.";
        s[8] = "Please enter the total weight of Plastic containers.";
        s[9] = "Please enter the total weight of Other trash.";
        s[10] = "Now we're going to start Compost. Please enter the total weight of Soiled paper and cardboard.";
        s[11] = "Please enter the total weight of Compostable cups.";
        s[12] = "Please enter the total weight of Compostable clamshells.";
        s[13] = "Please enter the total weight of Organics/Food.";
        s[14] = "Please enter the total weight of Other compost.";
        s[15] = "Now we're going to start Others. Please enter the total weight of Hazardous material.";
        s[16] = "Please enter the total weight of Batteries.";
        s[17] = "Please enter the total weight of E-waste.";
        s[18] = "Please enter the total weight of Wood.";
        for(int i = 0; i<s.length; i++){
            list.add(s[i]);
        }
    }

    // -------------------------- StreamsFrag methods ---------------------
    public void onRecycleFragClick(){
        currStream = "recyclables";
        if(bin.streams.containsKey("recyclables")){
            bin.streams.remove("recyclables");
        }
        RecycleFrag frag = RecycleFrag.newInstance(recycledone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onCompostFragClick(){
        currStream = "compost";
        if(bin.streams.containsKey("compost")){
            bin.streams.remove("compost");
        }
        CompostFrag frag = CompostFrag.newInstance(compostdone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onTrashFragClick(){
        currStream = "trash";
        if(bin.streams.containsKey("trash")){
            bin.streams.remove("trash");
        }
        TrashFrag frag = TrashFrag.newInstance(trashdone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOtherFragClick(){
        if(bin.streams.containsKey("other")){
            bin.streams.remove("other");
        }
        currStream = "other";
        OtherFrag frag = OtherFrag.newInstance(otherdone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onBackStreamFragClick(){
        // add to database
        // ----------
        this.audit.addBin(bin);
        // ----------
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void onDoneStreamFragClick(){
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    // ---------------------------------------------------------------------
    // --------------------------- RecycleFrag -----------------------------
    public void onCleanPACFragClick(){
        Entry e = recyclables.getEntries().get("clean paper and cardboard");
        InputFrag cleanPAC;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            recyclables.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            cleanPAC = InputFrag.newInstance("cleanPAC", c, v ,w);
        }else {
            cleanPAC = InputFrag.newInstance("cleanPAC", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, cleanPAC);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onCansFragClick(){
        Entry e = recyclables.getEntries().get("cans");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            recyclables.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("cans", c, v ,w);
        }else {
            f = InputFrag.newInstance("cans", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onPbjFragClick(){
        Entry e = recyclables.getEntries().get("plastic bottles and jugs");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            recyclables.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("plastic bottles and jugs", c, v ,w);
        }else {
            f = InputFrag.newInstance("plastic bottles and jugs", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onGlassbottlesFragClick(){
        Entry e = recyclables.getEntries().get("glass bottles");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            recyclables.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("glass bottles", c, v ,w);
        }else {
            f = InputFrag.newInstance("glass bottles", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOther2FragClick(){
        Entry e = recyclables.getEntries().get("other2");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            recyclables.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("other2", c, v ,w);
        }else {
            f = InputFrag.newInstance("other2", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onRecycleBackFragClick(){
        bin.addStream("Recyclables", recyclables);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
        Log.i(TAG, "num of streams in bin = " + bin.streams.size());
    }
    // ---------------------------------------------------------------------
    // --------------------------- TrashFrag -------------------------------
    public void onPlasticbagsFragClick(){
        Entry e = trash.getEntries().get("plastic bags");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("plastic bags", c, v ,w);
        }else {
            f = InputFrag.newInstance("plastic bags", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onWrappersFragClick(){
        Entry e = trash.getEntries().get("wrappers");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("wrappers", c, v ,w);
        }else {
            f = InputFrag.newInstance("wrappers", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onNoncompostablecupsFragClick(){
        Entry e = trash.getEntries().get("non-compostable cups");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("non-compostable cups", c, v ,w);
        }else {
            f = InputFrag.newInstance("non-compostable cups", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onPlasticcontainersFragClick(){
        Entry e = trash.getEntries().get("plastic containers");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("plastic containers", c, v ,w);
        }else {
            f = InputFrag.newInstance("plastic containers", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOther3FragClick(){
        Entry e = trash.getEntries().get("other3");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("other3", c, v ,w);
        }else {
            f = InputFrag.newInstance("plastic bags", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onTrashBackFragClick(){
        bin.addStream("trash", trash);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
    // ---------------------------------------------------------------------
    // ------------------------- CompostFrag -------------------------------
    public void onSoiledPACFragClick(){
        Entry e = compost.getEntries().get("soiled paper and cardboard");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("soiled paper and cardboard", c, v ,w);
        }else {
            f = InputFrag.newInstance("soiled paper and cardboard", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onCompostablecupsFragClick(){
        Entry e = compost.getEntries().get("compostable cups");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("compostable cups", c, v ,w);
        }else {
            f = InputFrag.newInstance("compostable cups", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onCompostabeclamshellsFragClick(){
        Entry e = compost.getEntries().get("compostable clamshells");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("compostable clamshells", c, v ,w);
        }else {
            f = InputFrag.newInstance("compostable clamshells", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOrganicsFragClick(){
        Entry e = compost.getEntries().get("organics");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("organics", c, v ,w);
        }else {
            f = InputFrag.newInstance("organics", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOther4FragClick(){
        Entry e = compost.getEntries().get("other4");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("other4", c, v ,w);
        }else {
            f = InputFrag.newInstance("other4", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onBackCompostFragClick(){
        bin.addStream("compost", compost);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }
    // ----------------------------------------------------------------------
    // ------------------------- OtherFrag ----------------------------------
    public void onHazardousFragClick(){
        Entry e = other.getEntries().get("hazardous");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("hazardous", c, v ,w);
        }else {
            f = InputFrag.newInstance("hazardous", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onBatteriesFragClick(){
        Entry e = other.getEntries().get("batteries");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("batteries", c, v ,w);
        }else {
            f = InputFrag.newInstance("batteries", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onEwasteFragClick(){
        Entry e = other.getEntries().get("ewaste");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            trash.deleteEntry(e);
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("ewaste", c, v ,w);
        }else {
            f = InputFrag.newInstance("ewaste", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onWoodFragClick(){
        Entry e = other.getEntries().get("wood");
        InputFrag f;
        ProgressBar pb = findViewById(R.id.progbar);
        pb.incrementProgressBy(1);
        if(e!=null){
            pb = findViewById(R.id.progbar);
            pb.incrementProgressBy(-1);
            trash.deleteEntry(e);
            double c = e.getCount();
            double v = e.getVolume();
            double w = e.getWeight();
            f = InputFrag.newInstance("wood", c, v ,w);
        }else {
            f = InputFrag.newInstance("wood", -1, -1, -1);
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frag_container, f);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public void onOtherBackFragClick(){
        bin.addStream("other", other);
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();
    }

    // ------------------------------------------------------------------------
    // ------------------- Code to create waste audit -------------------------

    private WasteStream createRecycleStream(){
        WasteStream recyclables = new WasteStream("Recyclables");
        return recyclables;
    }

    private int getTotalProgress(){
        return tra_prog + rec_prog + com_prog + oth_prog;
    }
}
