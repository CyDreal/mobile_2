package com.example.main_mobile;

import java.util.ArrayList;
import java.util.List;

public class Latihan_1_1_Repository {
    private static  Latihan_1_1_Repository repo;
    private ArrayList<Latihan_1_1_Models> modelsList = new ArrayList<>();

    public Latihan_1_1_Repository() {
        modelsList.add(new Latihan_1_1_Models("A22.2021.00001","Anisa Rahmawati",
                "https://blue.kumparan.com/image/upload/"+
                        "fl_progressive,fl_lossy,c_fill,q_auto:best,w_640/"+
                        "v1634025439/0180f6274acc867535d07275221c1db5.jpg"));
        modelsList.add(new Latihan_1_1_Models("A22.2021.00002","Mikel Johan",
                "https://static.promediateknologi.id/crop/0x0:0x0/0x0/"+
                        "webp/photo/p2/83/2023/06/28/OK-OPINI-FARRY-81530641.jpeg"));
        modelsList.add(new Latihan_1_1_Models("A22.2021.00003","Aditya Santoso",
                "https://encrypted-tbn0.gstatic.com/" +
                        "oBUJNuTDlXMopW3vmMsKnQ_MOPRT-uNzFc&usqp=CAU"));
    }
    public static Latihan_1_1_Repository getRepo() {
       if (repo == null) {
           repo = new Latihan_1_1_Repository();
       }
       return repo;
    }

    public ArrayList<Latihan_1_1_Models> getModelsList() {
        return modelsList;
    }
}
