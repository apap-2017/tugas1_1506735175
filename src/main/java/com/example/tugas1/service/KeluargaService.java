package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KeluargaModel;

public interface KeluargaService {

	KeluargaModel selectKeluarga(String kkKeluarga);

	void updateKeluarga (KeluargaModel keluarga);

	KeluargaModel selectKeluargaFromId(int idKeluargaPendudukBaru);
	
	List<KeluargaModel> getSimilarNKK(String partNkk);

	int getLastIdKeluarga();
	
	void addKeluarga(KeluargaModel keluarga);
}
