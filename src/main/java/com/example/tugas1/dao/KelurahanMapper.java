package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.example.tugas1.model.KelurahanModel;

@Mapper
public interface KelurahanMapper {

	@Select("SELECT * FROM kelurahan WHERE nama_kelurahan = #{namaKelurahan}")
	@Results(value = {
			@Result(property = "idKelurahan", column = "id"),
			@Result(property = "kodeKelurahan", column = "kode_kelurahan"),
			@Result(property = "namaKelurahan", column = "nama_kelurahan"),
	})
	KelurahanModel selectKelurahan (@Param("namaKelurahan") String nama);

	@Select("SELECT * FROM kelurahan WHERE id = #{idKelurahan}")
	@Results(value = {
			@Result(property = "idKelurahan", column = "id"),
			@Result(property = "kodeKelurahan", column = "kode_kelurahan"),
			@Result(property = "namaKelurahan", column = "nama_kelurahan"),
	})
	KelurahanModel selectKelurahanById (@Param("idKelurahan") String idKelurahan);	
	
	@Select("SELECT id AS idKelurahan, kode_kelurahan AS kodeKelurahan, nama_kelurahan AS namaKelurahan FROM kelurahan WHERE id_kecamatan = #{idKecamatan}")	
	List<KelurahanModel> selectKelurahanFromKecamatan(@Param("idKecamatan") String idKecamatan);    
	
}

