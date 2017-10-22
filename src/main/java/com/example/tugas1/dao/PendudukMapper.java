package com.example.tugas1.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.tugas1.model.PendudukModel;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

@Mapper
public interface PendudukMapper {

	@Select("SELECT * FROM penduduk JOIN keluarga ON penduduk.id_keluarga = keluarga.id  JOIN kelurahan ON keluarga.id_kelurahan = kelurahan.id JOIN kecamatan ON kelurahan.id_kecamatan = kecamatan.id JOIN kota ON kecamatan.id_kota = kota.id WHERE nik = ${nik}")
	@Results(value = {
			@Result(property = "idPenduduk", column = "id"),
			@Result(property = "nikPenduduk", column = "nik"),
			@Result(property = "namaLengkap", column = "nama"),
			@Result(property = "tempatLahir", column = "tempat_lahir"),
			@Result(property = "tanggalKelahiran", column = "tanggal_lahir"),
			@Result(property = "gender", column = "jenis_kelamin"),
			@Result(property = "kewarganegaraan", column = "is_wni"),
			@Result(property = "idKeluarga", column = "id_keluarga"),
			@Result(property = "kepercayaan", column = "agama"),
			@Result(property = "pekerjaan", column = "pekerjaan"),
			@Result(property = "statusKawin", column = "status_perkawinan"),
			@Result(property = "statusDalamKeluarga", column = "status_dalam_keluarga"),
			@Result(property = "golonganDarah", column = "golongan_darah"),
			@Result(property = "tiada", column = "is_wafat"),
			
			@Result(property = "alamat", column = "alamat"),
			@Result(property = "RT", column = "RT" ),
			@Result(property = "RW", column = "RW" ),
			@Result(property = "kelurahanDesa", column = "nama_kelurahan" ),
			@Result(property = "kecamatan", column = "nama_kecamatan" ),
			@Result(property = "kota", column = "nama_kota" ),

	})
	PendudukModel selectInfoPenduduk (@Param("nik") String nik);
	
    @Update("UPDATE penduduk SET nik = #{nikPenduduk}, nama = #{namaLengkap}, tempat_lahir = #{tempatLahir}, tanggal_lahir = #{tanggalKelahiran}, jenis_kelamin = #{gender}, is_wni = #{kewarganegaraan}, agama = #{kepercayaan}, pekerjaan = #{pekerjaan}, status_perkawinan = #{statusKawin}, is_wafat = #{tiada}  WHERE id = #{idPenduduk} ")
    void updatePenduduk (PendudukModel penduduk);	
    
    @Insert("INSERT INTO penduduk (id, nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat) VALUES (#{idPenduduk}, #{nikPenduduk}, #{namaLengkap}, #{tempatLahir}, #{tanggalKelahiran}, #{gender}, #{kewarganegaraan}, #{idKeluarga}, #{kepercayaan}, #{pekerjaan}, #{statusKawin}, #{statusDalamKeluarga}, #{golonganDarah}, #{tiada} )")
    void addPenduduk (PendudukModel penduduk);
    
    @Select("SELECT * FROM penduduk")  
    List<PendudukModel> selectAllPenduduk();

    @Select("SELECT nik AS nikPenduduk FROM penduduk WHERE nik LIKE #{partNik}")
    List<PendudukModel> selectSimilarNIK(@Param("partNik") String partNik);

    @Select("SELECT id FROM penduduk ORDER BY id DESC LIMIT 1;")
    int getLastId();     
    
    @Update("UPDATE penduduk SET is_wafat = 1 WHERE nik = #{nik}")
    void deactivatePenduduk (String nik);
    
	@Select("SELECT nik AS nikPenduduk, nama AS namaLengkap, jenis_kelamin AS gender FROM penduduk JOIN keluarga ON penduduk.id_keluarga = keluarga.id WHERE keluarga.id_kelurahan = #{idKelurahan}")	
	List<PendudukModel> selectPendudukFromKelurahan(@Param("idKelurahan") String idKelurahan);

}
