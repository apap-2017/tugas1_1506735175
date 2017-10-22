package com.example.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KotaKabupatenModel;
import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KelurahanService;
import com.example.tugas1.service.KotaKabupatenService;
import com.example.tugas1.service.PendudukService;

@Controller
public class PendudukController {

	@Autowired
	PendudukService pendudukDAO;
	
	@Autowired
	KeluargaService keluargaDAO;

	@Autowired
	KotaKabupatenService kotaKabupatenDAO;
	
	@Autowired
	KecamatanService kecamatanDAO;
	
	@Autowired
	KelurahanService kelurahanDAO;

  	@Autowired
  	KeluargaMapper keluargaMapper;  

    @RequestMapping("/")
    public String index ()
    {
        return "index";
    }
    
	@RequestMapping("/penduduk")
	public String viewPenduduk (
		@RequestParam(value="nik") String nikk, Model model) {	
		if (nikk.isEmpty()) {
			model.addAttribute("jenis", "nik");
			model.addAttribute("nik", "kosong");
			return "not-found";			
		}
		
		PendudukModel penduduk = pendudukDAO.selectPenduduk(nikk);
		if (penduduk != null) {
			model.addAttribute("penduduk", penduduk);
			return "viewpenduduk";
		} else {
			model.addAttribute("jenis", "nik");
			model.addAttribute("nik", nikk);
			return "not-found";
		}
				
	}
	
    @RequestMapping("/penduduk/tambah")
    public String addPenduduk (){
        return "addpenduduk";
    }    
    
    
    @RequestMapping("/penduduk/tambah/submit")
    public String submitPenduduk (
    	@RequestParam(value = "namanya") String namaPendudukBaru,
    	@RequestParam(value = "tempatLahirnya") String tempatLahirPendudukBaru,
    	@RequestParam(value = "tanggalLahirnya") String tanggalLahirPendudukBaru,
    	@RequestParam(value = "jenisKelaminnya") boolean jenisKelaminPendudukBaru,
    	@RequestParam(value = "kewarganegaraannya") boolean kewarganegaraanPendudukBaru,
    	@RequestParam(value = "idKeluarganya") int idKeluargaPendudukBaru,
    	@RequestParam(value = "agamanya") String agamaPendudukBaru,
    	@RequestParam(value = "pekerjaannya") String pekerjaanPendudukBaru,
    	@RequestParam(value = "statusKawinnya") String statusKawinPendudukBaru,
    	@RequestParam(value = "statusDalamKeluarganya") String statusDalamKeluargaBaru,
    	@RequestParam(value = "golonganDarahnya") String golonganDarahPendudukBaru,
    	@RequestParam(value = "statusKematiannya") boolean statusKematianPendudukBaru,
    	Model model
    	)
    {
    	
    	PendudukModel pendudukBaru = new PendudukModel (
    			0,
    			null,
    			namaPendudukBaru,
    			tempatLahirPendudukBaru,
    			tanggalLahirPendudukBaru,
    			jenisKelaminPendudukBaru,
    			kewarganegaraanPendudukBaru,
    			idKeluargaPendudukBaru,
    			agamaPendudukBaru,
    			pekerjaanPendudukBaru,
    			statusKawinPendudukBaru,
    			statusDalamKeluargaBaru,
    			golonganDarahPendudukBaru,
    			statusKematianPendudukBaru, 
    			null, null, null, null, null, null, null
    			);
    	
    	KeluargaModel cariKeluarganya = keluargaDAO.selectKeluargaFromId(idKeluargaPendudukBaru);

        if(cariKeluarganya == null){
            return "vnoiad";
        }  
        
        String kecamatanHey = cariKeluarganya.getKecamatan();
        KecamatanModel kecamatan = kecamatanDAO.selectKecamatan(kecamatanHey);
    	String kodeKecamatan = kecamatan.getKodeKecamatan();

    	
    	String[] splitTanggal = tanggalLahirPendudukBaru.split("-");
    	String tanggal = splitTanggal[2];
    	String bulan = splitTanggal[1];
    	String tahun = splitTanggal[0].substring(2);
    	
    	if(jenisKelaminPendudukBaru == true) {
    		int tanggalInt = Integer.parseInt(tanggal);
    		tanggalInt = tanggalInt + 40;
    		tanggal = tanggalInt + "";
    	}
            	
    	String nikTemp = kodeKecamatan.substring(0, 6) + tanggal + bulan + tahun;
    	List<PendudukModel> nikMirip = pendudukDAO.getSimilarNIK(nikTemp + "%");
    	String nomorUrut = "0001";
    	
    	if (nikMirip.size() >= 1) {
    		int banyaknya = nikMirip.size();
    		System.out.println("JUMLAH YANG MIRIP " + nikMirip.size());
    		int indeksAkhir = banyaknya - 1;
    		
    		String nikLatest = nikMirip.get(indeksAkhir).getNikPenduduk();
    		System.out.println("=====NIK LATEST==" + nikLatest);
    		String codeLatest = nikLatest.substring(12);
    		int codeLatest2 = Integer.parseInt(codeLatest) + 1;
    		System.out.println("CALON NIK ==" + codeLatest2);
    		nomorUrut = codeLatest2 + "";
    	}
    	
    	if (nomorUrut.length() == 1) {
    		nomorUrut = "000" + nomorUrut;
    	} else if (nomorUrut.length() == 2) {
    		nomorUrut = "00" + nomorUrut;
    	} else if (nomorUrut.length() == 3) {
    		nomorUrut = "0" + nomorUrut;
    	}
    	
    	String nikFinal = nikTemp + nomorUrut;
    	
    	int lastIdPenduduk = pendudukDAO.getLastIdPenduduk();
    	int idPendudukBaru = lastIdPenduduk + 1;
    	
    	pendudukBaru.setIdPenduduk(idPendudukBaru);
    	pendudukBaru.setNikPenduduk(nikFinal);

    	pendudukDAO.addPenduduk(pendudukBaru); 
    	model.addAttribute("jenisFitur", "tambahPenduduk");
    	model.addAttribute("nikBaru", nikFinal);
    	return "success";
    }
    
    @RequestMapping("/penduduk/ubah/{nik}")
    public String update (Model model, @PathVariable(value = "nik") String nik)
    {
    	PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    	if(penduduk != null) {
    		model.addAttribute("penduduk", penduduk);
    		return "form-update-penduduk";
    	}
    	return "not-found";
    }
	    
    @RequestMapping(value = "/penduduk/nonaktifkan")
    public String deactivate (Model model, @RequestParam(value = "nik") String nik) {
    	if (nik == null) {
        	return "not-found";    		
    	} 
    		PendudukModel penduduk = pendudukDAO.selectPenduduk(nik);
    		
    		pendudukDAO.deactivatePenduduk(nik);
    		
    		
    		System.out.println(penduduk.getIdKeluarga());
    		
    		
    		String idKeluarga = penduduk.getIdKeluarga() + "";
    		
    		List<PendudukModel> anggotaKeluarga = keluargaMapper.selectAnggotaKeluarga(idKeluarga);
      		keluargaMapper.UpdateKetidakBerlakuanKeluarga(idKeluarga, 1);
      		for(PendudukModel anggota : anggotaKeluarga) {
      			if(anggota.isTiada() == false) {
      				keluargaMapper.UpdateKetidakBerlakuanKeluarga(idKeluarga, 0);
      			}
      		}
      		model.addAttribute("jenisFitur", "nonaktif");
      		model.addAttribute("nikTelahTiada", nik);
      		return "success";    		
    	
    }
    
  	@RequestMapping(value = "/penduduk/ubah/submit", method = RequestMethod.POST)    
    public String updateSubmit (Model model, PendudukModel penduduk){
  		
  				System.out.println(penduduk.getTanggalKelahiran());
  				System.out.println("NIK LAMA >>> " + penduduk.getNikPenduduk());
  				
  				String tanggalLahir = penduduk.getTanggalKelahiran();
  		    	String[] splitTanggal = tanggalLahir.split("-");
  		    	String hari = splitTanggal[2];
  		    	String bulan = splitTanggal[1];
  		    	String tahun = splitTanggal[0].substring(2);
  		    	
  		    	String nikForTanggal = hari + bulan + tahun;
  		    	
  		    	String nikAwal = penduduk.getNikPenduduk();
  		    	String kodeKecamatan = nikAwal.substring(0, 6);
  		    	String kodeUrut = nikAwal.substring(12);
  		    	
  		    	String nikTemp = kodeKecamatan + nikForTanggal;
  		    	
  		    	boolean isPerempuan = penduduk.isGender();
  		    	
	    		int tanggalHari = Integer.parseInt(hari);  		    	
  		    	//jika skrg perempuan dan sebelumnya adalah laki2
  		    	if(isPerempuan == true) {
  		    		//tanggal sebelumnya masih dalam format laki2
  		    		if (tanggalHari < 40) {
  		    			tanggalHari = tanggalHari + 40; 
  		    		}
  		    	//jika skrg laki2 dan sebelumnya perempuan  		    		
  		    	} else if (isPerempuan == false) {
  		    		//jika tanggal sebelumnya masih perempuan
  		    		if (tanggalHari > 40) {
  		    			tanggalHari = tanggalHari - 40;
  		    		}
  		    	}
  		    	
  		    	String fixTanggalHari = "";
  		    	if(tanggalHari < 10) {
  		    		fixTanggalHari = "0" + tanggalHari;
  		    	} else {
  		    		fixTanggalHari = tanggalHari + "";
  		    	}
  		    	
  		    	String nikTerbaruLagi = kodeKecamatan + fixTanggalHari + bulan + tahun;
  		    	System.out.println("NIK BARU >>> " + nikTemp);
  		    	System.out.println("NIK GEND >>> " + nikTerbaruLagi);
  		    	
  		    	
  		    	List<PendudukModel> nikMirip = pendudukDAO.getSimilarNIK(nikTerbaruLagi + "%");
  		    	if (nikMirip.size() >= 1) {
  		    		int banyaknya = nikMirip.size();
  		    		int indeksAkhir = banyaknya - 1;
  		    		String nikLatest = nikMirip.get(indeksAkhir).getNikPenduduk();
  		    		String codeLatest = nikLatest.substring(12);
  		    		int codeLatest2 = Integer.parseInt(codeLatest) + 1;
  		    		kodeUrut = codeLatest2 + "";
  		    	}
  		    	
  		    	if (kodeUrut.length() == 1) {
  		    		kodeUrut = "000" + kodeUrut;
  		    	} else if (kodeUrut.length() == 2) {
  		    		kodeUrut = "00" + kodeUrut;
  		    	} else if (kodeUrut.length() == 3) {
  		    		kodeUrut = "0" + kodeUrut;
  		    	}
  		    	  		    	
  		    	String nikFinal = nikTerbaruLagi + kodeUrut;
  		    	System.out.println("NIK FINL >>> " + nikFinal);
  		    	penduduk.setNikPenduduk(nikFinal);
  		    	
  				pendudukDAO.updatePenduduk(penduduk);
  				model.addAttribute("jenisFitur", "updatePenduduk");
  				model.addAttribute("nikBaru", nikFinal);
    			return "success";			
  	 }	 
  	
  	@RequestMapping(value="/penduduk/cari")
  	public String findPenduduk (Model model, 
  			@RequestParam(value="kt", required=false) String idKota, 
  			@RequestParam(value="kc", required=false) String idKecamatan, 
  			@RequestParam(value="kl", required=false) String idKelurahan) 
  	{
  		List<KotaKabupatenModel> daftarKota = kotaKabupatenDAO.selectAllKotaKabupaten();
		/*for(KotaKabupatenModel anggota : daftarKota){
			System.out.println(anggota.getNamaKotaKabupaten());
		} */ 		
  		model.addAttribute("kota", daftarKota); 
  		
  		if(idKota != null && idKecamatan == null && idKelurahan == null) {
  			List<KecamatanModel> daftarKecamatan = kecamatanDAO.selectKecamatanFromKota(idKota);	  			
  			KotaKabupatenModel kotanya = kotaKabupatenDAO.selectKotaKabupaten(idKota);
  			String namaKota = kotanya.getNamaKotaKabupaten();
  			
  			model.addAttribute("idKota", idKota);
  			model.addAttribute("namaKota", namaKota);
  			model.addAttribute("kecamatan", daftarKecamatan);
  			return "searchresultkota";
  			
  		} else if (idKecamatan != null) {
  			List<KelurahanModel> daftarKelurahan = kelurahanDAO.selectKelurahanFromKecamatan(idKecamatan);
  			KecamatanModel kecamatannya = kecamatanDAO.selectKecamatanById(idKecamatan);
  			String namaKecamatan = kecamatannya.getNamaKecamatan();

  			model.addAttribute("idKecamatan", idKecamatan);
  			model.addAttribute("namaKecamatan", namaKecamatan);
  			model.addAttribute("kelurahan", daftarKelurahan);
  			return "searchresultkecamatan";
  			
  		} else if (idKelurahan != null) {
  			List<PendudukModel> daftarPenduduk = pendudukDAO.selectPendudukFromKelurahan(idKelurahan);
  			KelurahanModel kelurahannya = kelurahanDAO.selectKelurahanById(idKelurahan);
  			String namaKelurahan = kelurahannya.getNamaKelurahan();
  			
  			model.addAttribute("idKelurahan", idKelurahan);
  			model.addAttribute("namaKelurahan", namaKelurahan);
  			model.addAttribute("penduduk", daftarPenduduk);
  			return "searchresultkelurahan";
  		}
  		
  		return "searchresult";
  	}
	 
}
