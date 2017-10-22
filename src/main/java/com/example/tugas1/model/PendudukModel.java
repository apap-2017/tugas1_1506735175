package com.example.tugas1.model;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

public class PendudukModel {

	private int idPenduduk;
	private String nikPenduduk;
	
	@NotNull
	@Size(min=1, max=256)
	private String namaLengkap;
	
	@NotNull
	@Size(min=1, max=256)	
	private String tempatLahir;
	
	@NotNull
	private String tanggalKelahiran;
	
	@NotNull
	@Size(min=1, max=1)
	private boolean gender;

	@NotNull
	@Size(min=1, max=1)	
	private boolean kewarganegaraan;

	@NotNull
	@Size(min=1, max=32)
	private int idKeluarga;
	
	@NotNull
	@Size(min=1, max=64)
	private String kepercayaan;
	
	@NotNull
	@Size(min=1, max=128)
	private String pekerjaan;
	
	@NotNull
	@Size(min=1, max=64)
	private String statusKawin;
	
	@NotNull
	@Size(min=1, max=64)
	private String statusDalamKeluarga;
	
	@NotNull
	@Size(min=1, max=16)
	private String golonganDarah;
	
	@NotNull
	@Size(min=1, max=1)
	private boolean tiada;
	
	private String alamat;
	private String RT;
	private String RW;
	private String kelurahanDesa;
	private String kecamatan;
	private String kota;
	
	private KeluargaModel keluarga;
	
}
