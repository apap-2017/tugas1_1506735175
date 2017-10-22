package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KecamatanModel;

@Mapper
public interface KecamatanMapper {

	@Select("SELECT * FROM kecamatan WHERE nama_kecamatan = #{namaKecamatan}")
	@Results(value = {
			@Result(property = "idKecamatan", column = "id"),
			@Result(property = "namaKecamatan", column = "nama_kecamatan"),
			@Result(property = "kodeKecamatan", column = "kode_kecamatan"),
	})
	KecamatanModel selectKecamatan (@Param("namaKecamatan") String nama);

	@Select("SELECT * FROM kecamatan WHERE id = #{idKecamatan}")
	@Results(value = {
			@Result(property = "idKecamatan", column = "id"),
			@Result(property = "namaKecamatan", column = "nama_kecamatan"),
			@Result(property = "kodeKecamatan", column = "kode_kecamatan"),
	})
	KecamatanModel selectKecamatanById (@Param("idKecamatan") String id);	
	
	@Select("SELECT id AS idKecamatan, kode_kecamatan AS kodeKecamatan, nama_kecamatan AS namaKecamatan FROM kecamatan WHERE id_kota = #{idKota}")
	List<KecamatanModel> selectKecamatanFromKota(@Param("idKota") String idKota);    
	
}

