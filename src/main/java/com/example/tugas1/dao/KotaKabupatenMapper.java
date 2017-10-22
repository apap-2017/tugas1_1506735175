package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KotaKabupatenModel;

@Mapper
public interface KotaKabupatenMapper {

	@Select("SELECT * FROM kota WHERE id = #{idKotaKabupaten}")
	@Results(value = {
			@Result(property = "idKotaKabupaten", column = "id"),
			@Result(property = "namaKotaKabupaten", column = "nama_kota"),
			@Result(property = "kodeKotaKabupaten", column = "kode_kota"),
	})
	KotaKabupatenModel selectKotaKabupaten (@Param("idKotaKabupaten") String idKotaKabupaten);    

    @Select("SELECT id AS idKotaKabupaten, nama_kota AS namaKotaKabupaten, kode_kota AS kodeKotaKabupaten FROM kota")  
    List<KotaKabupatenModel> selectAllKotaKabupaten();
}

