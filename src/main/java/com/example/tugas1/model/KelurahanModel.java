package com.example.tugas1.model;

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

public class KelurahanModel {

	private int idKelurahan;
	private String kodeKelurahan;
	private String namaKelurahan;

}
