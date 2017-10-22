package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.PendudukModel;

public interface PendudukService {

	PendudukModel selectPenduduk (String nik);
	
    void updatePenduduk (PendudukModel penduduk);

    void addPenduduk (PendudukModel penduduk);
    
    void deactivatePenduduk (String nik);

    List<PendudukModel> selectAllPenduduk ();

    List<PendudukModel> getSimilarNIK(String partNik);

    int getLastIdPenduduk();
    
    List<PendudukModel> selectPendudukFromKelurahan(String idKelurahan);
}
