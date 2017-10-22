package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KecamatanModel;

public interface KecamatanService {

	KecamatanModel selectKecamatan(String namaKecamatan);
	KecamatanModel selectKecamatanById(String idKecamatan);
	List<KecamatanModel> selectKecamatanFromKota(String idKota);
}
