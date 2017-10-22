package com.example.tugas1.model;


import java.util.List;

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

public class KeluargaModel {

	private String nomorIdKeluarga;
	private String nomorKartuKeluarga;
	private String alamatKeluarga;
	private String rtKeluarga;
	private String rwKeluarga;
	private String kelurahanDesa;
	private String kecamatan;
	private String kota;
	private int ketidakberlakuan;
	private List<PendudukModel> anggota;
	private int idKelurahanKeluarga;	
}
