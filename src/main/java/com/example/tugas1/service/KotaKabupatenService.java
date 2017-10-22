package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.model.KotaKabupatenModel;

public interface KotaKabupatenService {

	KotaKabupatenModel selectKotaKabupaten(String idKotaKabupaten);

	List<KotaKabupatenModel> selectAllKotaKabupaten();
	
}
