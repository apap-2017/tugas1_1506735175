package com.example.tugas1.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Many;
import java.util.List;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;

@Mapper
public interface KeluargaMapper {

	@Select("SELECT * FROM keluarga JOIN kelurahan ON keluarga.id_kelurahan = kelurahan.id JOIN kecamatan ON kelurahan.id_kecamatan = kecamatan.id JOIN kota ON kecamatan.id_kota = kota.id WHERE nomor_kk = #{kkKeluarga}")
	@Results(value = {
			@Result(property = "nomorIdKeluarga", column = "id"),
			@Result(property = "nomorKartuKeluarga", column = "nomor_kk"),
			@Result(property = "alamatKeluarga", column = "alamat"),
			@Result(property = "rtKeluarga", column = "RT"),
			@Result(property = "rwKeluarga", column = "RW"),
			@Result(property = "kelurahanDesa", column = "nama_kelurahan" ),
			@Result(property = "kecamatan", column = "nama_kecamatan" ),
			@Result(property = "kota", column = "nama_kota" ),
			@Result(property = "ketidakberlakuan", column = "is_tidak_berlaku"),
			@Result(property = "anggota", column = "id", javaType = List.class,
			many = @Many(select = "selectAnggotaKeluarga")),
	})
	KeluargaModel selectInfoKeluarga (@Param("kkKeluarga") String nomor_kk);

	@Select("SELECT nik AS nikPenduduk,  nama AS namaLengkap, tempat_lahir AS tempatLahir, tanggal_lahir AS tanggalKelahiran, jenis_kelamin AS gender, is_wni AS kewarganegaraan, id_keluarga AS idKeluarga, agama AS kepercayaan, pekerjaan AS pekerjaan, status_perkawinan AS statusKawin, status_dalam_keluarga AS statusDalamKeluarga, golongan_darah AS golonganDarah, is_wafat AS tiada   FROM penduduk  WHERE id_keluarga = #{id}")
	List<PendudukModel> selectAnggotaKeluarga (@Param("id") String id_keluarga);
	
    @Update("UPDATE keluarga SET nomor_kk = #{nomorKartuKeluarga}, alamat = #{alamatKeluarga}, RT = #{rtKeluarga}, RW = #{rwKeluarga}, id_kelurahan = #{idKelurahanKeluarga}   WHERE id = #{nomorIdKeluarga} ")
    void updateKeluarga (KeluargaModel keluarga);	

	@Select("SELECT * FROM keluarga JOIN kelurahan ON keluarga.id_kelurahan = kelurahan.id JOIN kecamatan ON kelurahan.id_kecamatan = kecamatan.id JOIN kota ON kecamatan.id_kota = kota.id WHERE keluarga.id = #{familyId}")
	@Results(value = {
			@Result(property = "nomorIdKeluarga", column = "id"),
			@Result(property = "nomorKartuKeluarga", column = "nomor_kk"),
			@Result(property = "kecamatan", column = "nama_kecamatan" ),
	})
	KeluargaModel selectInfoKeluargaById (@Param("familyId") int id);

    @Select("SELECT nomor_kk AS nomorKartuKeluarga FROM keluarga WHERE nomor_kk LIKE #{partNkk}")
	List<KeluargaModel> selectSimilarNKK (@Param("partNkk") String partNkk);    
	
    @Select("SELECT id FROM keluarga ORDER BY id DESC LIMIT 1;")
    int getLastId();   
    
    @Insert("INSERT INTO keluarga (id, nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku) VALUES (#{nomorIdKeluarga}, #{nomorKartuKeluarga}, #{alamatKeluarga}, #{rtKeluarga}, #{rwKeluarga}, #{idKelurahanKeluarga}, 0)")
    void addKeluarga (KeluargaModel keluarga);
    
    @Update("UPDATE keluarga SET is_tidak_berlaku = #{flag} WHERE id = #{idne}")
    void UpdateKetidakBerlakuanKeluarga(@Param("idne") String id, @Param("flag") int flag);
	
}

