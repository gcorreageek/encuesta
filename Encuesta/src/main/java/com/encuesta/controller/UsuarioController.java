package com.encuesta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.encuesta.model.Cargo;
import com.encuesta.model.Usuario;
import com.encuesta.service.UsuarioService;
@RequestMapping("/usuario")
@Controller
public class UsuarioController {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UsuarioService usuarioService;

	private String WebRootPath;
	
	@RequestMapping("/usuario_ir_subirexcel.html")
	public String irSubirUsuarioExcel(HttpServletRequest request,Model m){ 
		return "/mantenimiento/usuario_subirexcel";
	}
	@RequestMapping("/usuario_upload.html")
	public String upload(HttpServletRequest request,Model m
			,  @RequestParam(value="file", required=false) MultipartFile file
			){ 
		InputStream ie = null;
		try {
			String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath + File.separator + "temp");
            if (!dir.exists())
                dir.mkdirs();

            File serverFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename());
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			ie = new FileInputStream(serverFile);
//			new InputStrea
			POIFSFileSystem excelByte = new POIFSFileSystem(ie);
			HSSFWorkbook excel = new HSSFWorkbook(excelByte);
			HSSFSheet hojaVendedores = excel.getSheetAt(0);
			
			boolean esCabecera = true;
			Usuario docente = null;
			List<Usuario> lstDocente = new ArrayList<Usuario>();
			log.info("Volcado de excel!");
			for (Iterator iterator = hojaVendedores.iterator(); iterator.hasNext();) {
				HSSFRow fila = (HSSFRow) iterator.next();
				if (esCabecera){ 
					esCabecera = false;
				}else {  
					docente = new Usuario();
					docente.setIdUsuario(0);
					docente.setNombre(fila.getCell(0).getRichStringCellValue().toString());
					Cargo cargo = new Cargo();
					cargo.setIdCargo(1);
					docente.setCargoDelUsuario(cargo);
					docente.setTipoUsuarioD(1);
//					docente.setNomdocente(fila.getCell(0).getRichStringCellValue().toString());
					lstDocente.add(docente);
				}
			}
			
			boolean res = (boolean) usuarioService.insertarMuchos(lstDocente);
			if(res){
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				ie.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "/mantenimiento/usuario_subirexcel";
	}
	public String getWebRootPath() {
		return WebRootPath;
	}
	public void setWebRootPath(String webRootPath) {
		WebRootPath = webRootPath;
	}
	
	
	
}
