package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KelurahanModel;

public interface KelurahanService {

	KelurahanModel selectKelurahan(String namaKelurahan);
	KelurahanModel selectKelurahanById(String idKelurahan);
	
	List<KelurahanModel> selectKelurahanFromKecamatan(String idKecamatan);
	
}
