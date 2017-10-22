package com.example.tugas1.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KelurahanService;

@Controller
public class KeluargaController {

	@Autowired
	KeluargaService keluargaDAO;

	@Autowired
	KecamatanService kecamatanDAO;	
	
	@Autowired
	KelurahanService kelurahanDAO;
	
	@RequestMapping("/keluarga")
	public String viewKeluarga (
		@RequestParam(value="nkk") String nkk, Model model) {
		
		KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
		if (keluarga != null) {
			model.addAttribute("keluarga", keluarga);
			return "viewkeluarga";			
		} else {
			model.addAttribute("jenis", "nkk");
			model.addAttribute("nkk", nkk);
			return "not-found";
		}
	}


    /*
  	@Autowired
  	KeluargaMapper abc;
  	
  	@RequestMapping(value = "/abc")
  	public String abc() {
  		
  		List<PendudukModel> anggotaKeluarga = abc.selectAnggotaKeluarga("172");
  		
  		for(PendudukModel anggota : anggotaKeluarga) {
  			System.out.println(">>>>>>>>>>>>>>>> " + anggota.toString());
  		}
  		
  		return "success-update";
  	}
  	*/
  	
    @RequestMapping("/keluarga/tambah")
    public String addKeluarga (){
        return "addkeluarga";
    }    	
  	
    @RequestMapping("/keluarga/tambah/submit")
    public String submitKeluarga (
    		@RequestParam(value = "alamatnya") String alamatKeluarga,
    		@RequestParam(value = "rtnya") String rtKeluarga,
    		@RequestParam(value = "rwnya") String rwKeluarga,
    		@RequestParam(value = "kelurahandesanya") String kelurahanKeluarga,
    		@RequestParam(value = "kecamatannya") String kecamatanKeluarga,
    		@RequestParam(value = "kotanya") String kotaKeluarga,
    		Model model
    	)
    {
    	
    	KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kecamatanKeluarga);
    	String kodeKecamatan = kecamatan.getKodeKecamatan();
    	kodeKecamatan = kodeKecamatan.substring(0, 6);
    	
    	KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(kelurahanKeluarga);
    	int idKelurahan = kelurahan.getIdKelurahan();
    	    	
    	int lastId = keluargaDAO.getLastIdKeluarga();
    	int idKeluargaBaru = lastId + 1;
    	String finalIdKeluarga = idKeluargaBaru + "";
    	
    	//System.out.println("<<<<<<<tanggal");
    	DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate tanggalNow = LocalDate.now();
    	String tanggalFormatted = formatDate.format(tanggalNow);
    	//System.out.println("<<<<<<<sipp lah");    
    	String[] tanggalSplit = tanggalFormatted.split("/");
    	String hari = tanggalSplit[0];
    	String bulan = tanggalSplit[1];
    	String tahun = tanggalSplit[2];
    	
    	String tempNKK = kodeKecamatan + hari + bulan + tahun;
    	System.out.println(tempNKK);

    	
    	List<KeluargaModel> nkkMirip = keluargaDAO.getSimilarNKK(tempNKK + "%");
    	String nomorUrut = "0001";
    	if (nkkMirip.size() >= 1) {
    		String nomorTerpilih = nkkMirip.get(nkkMirip.size() - 1).getNomorKartuKeluarga().substring(12);
    		int nomorTerpilihh = Integer.parseInt(nomorTerpilih) + 1;
    		nomorUrut = nomorTerpilihh + "";
    	}
    	
    	if (nomorUrut.length() == 1) {
    		nomorUrut = "000" + nomorUrut;
    	} else if (nomorUrut.length() == 2) {
    		nomorUrut = "00" + nomorUrut;
    	} else if (nomorUrut.length() == 3) {
    		nomorUrut = "0" + nomorUrut;
    	}
    	
    	String nkkFinal = tempNKK + nomorUrut;
    	
    	KeluargaModel keluargaBaru = new KeluargaModel (
    			finalIdKeluarga, 
    			nkkFinal, 
    			alamatKeluarga, 
    			rtKeluarga, 
    			rwKeluarga, 
    			kelurahanKeluarga, 
    			kecamatanKeluarga, 
    			kotaKeluarga, 
    			0, 
    			null, 
    			idKelurahan
    	);
    	    	
    	keluargaDAO.addKeluarga(keluargaBaru);
    	model.addAttribute("jenisFitur", "tambahKeluarga");
    	model.addAttribute("nkkBaru", nkkFinal);
        return "success";
    }    	

    @RequestMapping("/keluarga/ubah/{nkk}")
    public String update (Model model, @PathVariable(value = "nkk") String nkk)
    {
    	KeluargaModel keluarga = keluargaDAO.selectKeluarga(nkk);
    	if(keluarga != null) {
    		model.addAttribute("keluarga", keluarga);
    		return "form-update-keluarga";
    	}
    	return "not-found";
    }
    
    
  	@RequestMapping(value = "/keluarga/ubah/submit", method = RequestMethod.POST)
    
    public String updateSubmit (Model model, KeluargaModel keluarga)
  	{
   		
  		String namaKelurahan = keluarga.getKelurahanDesa();
  		
  		KelurahanModel kelurahan = kelurahanDAO.selectKelurahan(namaKelurahan);
  		int idKelurahan = kelurahan.getIdKelurahan();
  		
  		String kodeDomisili = kelurahan.getKodeKelurahan().substring(0, 6);
  		
  		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yy");
    	LocalDate tanggalJamanNow = LocalDate.now();
    	String tanggalFormatted = formatDate.format(tanggalJamanNow);
    	String[] tanggalSplit = tanggalFormatted.split("/");
    	String hari = tanggalSplit[0];
    	String bulan = tanggalSplit[1];
    	String tahun = tanggalSplit[2];
    	
    	String tempNKK = kodeDomisili + hari + bulan + tahun;

    	List<KeluargaModel> nkkMirip = keluargaDAO.getSimilarNKK(tempNKK + "%");
    	String nomorUrut = "0001";
    	if (nkkMirip.size() >= 1) {
    		String nomorTerpilih = nkkMirip.get(nkkMirip.size() - 1).getNomorKartuKeluarga().substring(12);
    		int nomorTerpilihh = Integer.parseInt(nomorTerpilih) + 1;
    		nomorUrut = nomorTerpilihh + "";
    	}
    	
    	if (nomorUrut.length() == 1) {
    		nomorUrut = "000" + nomorUrut;
    	} else if (nomorUrut.length() == 2) {
    		nomorUrut = "00" + nomorUrut;
    	} else if (nomorUrut.length() == 3) {
    		nomorUrut = "0" + nomorUrut;
    	}
    	
    	String nkkFinal = tempNKK + nomorUrut;
    	
  		//PENANGANAN JIKA TIDAK BERHASIL MENEMUKAN ID KELURAHAN YANG DICARI!!!    	
  		keluarga.setIdKelurahanKeluarga(idKelurahan);
  		keluarga.setNomorKartuKeluarga(nkkFinal);  		
  		keluargaDAO.updateKeluarga(keluarga);

		model.addAttribute("jenisFitur", "updateKeluarga");
		model.addAttribute("nkkBaru", nkkFinal);  		
   		return "success";
    			
    }	    
    
}

