package com.ventas.abm.manager.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventas.abm.manager.domain.Vendedor;
import com.ventas.abm.manager.models.VendedorDto;
import com.ventas.abm.manager.repository.VendedorRepository;

@Service
public class VendedorService {

	@Autowired
	VendedorRepository vendedorRepository;

	private static final Log logger = LogFactory.getLog(VendedorService.class);

	public Vendedor createVendedor(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Creando registro Vendedor: " + vendedor.getNombre() + " " + vendedor.getApellido();
		logger.info(logMensaje);
		try {
			if (vendedorRepository.findByCodigo(vendedor.getCodigo()) != null) {
				logMensaje = "Existe un registro con el legajo: " + vendedor.getCodigo();
				logger.error(logMensaje);
				throw new Exception(logMensaje);
			}
			Vendedor nuevoVendedor = new Vendedor();
			nuevoVendedor.setNombre(vendedor.getNombre());
			nuevoVendedor.setApellido(vendedor.getApellido());
			nuevoVendedor.setDocumento(vendedor.getDocumento());
			nuevoVendedor.setFechaNacimiento(vendedor.getFechaNacimiento());
			nuevoVendedor.setCodigo(vendedor.getCodigo());
			nuevoVendedor.setUsuario(vendedor.getUsuario());
			nuevoVendedor.setFechaAlta(new Date());
			nuevoVendedor.setFechaBaja(null);
			nuevoVendedor.setFechaModificacion(null);
			vendedorRepository.save(nuevoVendedor);
			logMensaje = "Registrado corretamente Vendedor: " + nuevoVendedor.getNombre() + " "
					+ nuevoVendedor.getApellido();
			logger.info(logMensaje);
			return nuevoVendedor;
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			throw new Exception(logMensaje);
		}
	}

	public Vendedor modifyVendedor(Vendedor vendedor) throws Exception {
		// TODO Auto-generated method stub
		String logMensaje = "Modificando registro Vendedor: " + vendedor.getIdVendedor();
		logger.info(logMensaje);
		Optional<Vendedor> vendedorOptional = vendedorRepository.findById(vendedor.getIdVendedor());
		if (vendedorOptional.isPresent()) {

			Vendedor vendedorEnBd = vendedorOptional.get();
			vendedorEnBd.setNombre(vendedor.getNombre());
			vendedorEnBd.setApellido(vendedor.getApellido());
			vendedorEnBd.setDocumento(vendedor.getDocumento());
			vendedorEnBd.setFechaNacimiento(vendedor.getFechaNacimiento());
			vendedorEnBd.setCodigo(vendedor.getCodigo());
			vendedorEnBd.setUsuario(vendedor.getUsuario());
			vendedorEnBd.setFechaAlta(vendedor.getFechaAlta());
			vendedorEnBd.setFechaBaja(vendedor.getFechaBaja());
			vendedorEnBd.setFechaModificacion(new Date());
			Vendedor vendedorModificado = vendedorRepository.save(vendedorEnBd);
			logMensaje = "Modificado correctamente Vendedor " + vendedorModificado.getNombre() + " "
					+ vendedorModificado.getApellido();
			logger.info(logMensaje);
			return vendedorModificado;
		} else {
			logMensaje = "No existe un Vendedor con el ID: " + vendedor.getIdVendedor();
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	public Vendedor deleteVendedor(long idVendedor) {
		// TODO Auto-generated method stub
		String logMensaje = "Baja de vendedor ID: " + idVendedor;
		logger.info(logMensaje);
		try {
			Vendedor vendedor = vendedorRepository.findById(idVendedor).get();
			vendedor.setFechaBaja(new Date());
			vendedorRepository.save(vendedor);
			logMensaje = "Se dio de baja Vendedor ID: " + idVendedor;
			logger.info(logMensaje);
			return vendedor;
		} catch (Exception e) {
			// TODO: handle exception
			logMensaje = e.getMessage();
			logger.error(logMensaje);
			e.printStackTrace();
			return null;
		}
	}

	public Vendedor getVendedorById(Long idVendedor) {
		// TODO Auto-generated method stub
		Vendedor vendedor = new Vendedor();
		Optional<Vendedor> vendedorOptional = vendedorRepository.findById(idVendedor);
		if (vendedorOptional.isPresent()) {
			vendedor = vendedorOptional.get();
			return vendedor;
		} else {
			logger.error("No se encontró Vendedor con el ID: " + idVendedor);
			return null;
		}
	}

	public List<VendedorDto> getVendedorByNombre(String nombre) throws Exception {
		// TODO Auto-generated method stub
		List<Vendedor> vendedor = vendedorRepository.findByNombre(nombre);
		if (vendedor.isEmpty()) {
			logger.error("No se encontro registros con el nombre: " + nombre);
			throw new Exception("No se encontro registros con el nombre: " + nombre);
		}
		List<VendedorDto> listaDto = mapDtoList(vendedor);

		return listaDto;
	}

	public List<VendedorDto> getVendedorByApellido(String apellido) throws Exception {
		// TODO Auto-generated method stub
		List<Vendedor> vendedor = vendedorRepository.findByApellido(apellido);
		if (vendedor.isEmpty()) {
			logger.error("No se encontro registros con el apellido: " + apellido);
			throw new Exception("No se encontro registros con el apellido: " + apellido);
		}
		List<VendedorDto> listaDto = mapDtoList(vendedor);

		return listaDto;
	}

	private List<VendedorDto> mapDtoList(List<Vendedor> vendedores) {
		// TODO Auto-generated method stub
		List<VendedorDto> dto = new ArrayList<>();

		for (Vendedor vendedor : vendedores) {
			VendedorDto vendedorDto = mapearDto(vendedor);
			dto.add(vendedorDto);
		}

		return dto;
	}

	public VendedorDto getVendedorByCodigo(Integer codigo) throws Exception {
		// TODO Auto-generated method stub
		Vendedor vendedor = vendedorRepository.findByCodigo(codigo);
		if (vendedor != null) {
			VendedorDto dto = mapearDto(vendedor);
			return dto;
		} else {
			String logMensaje = "No se encontro Vendedor con el Código " + codigo;
			logger.error(logMensaje);
			throw new Exception(logMensaje);
		}
	}

	private VendedorDto mapearDto(Vendedor vendedor) {
		// TODO Auto-generated method stub
		VendedorDto dto = new VendedorDto();

		if (vendedor.getIdVendedor() != null) {
			dto.setIdVendedor(vendedor.getIdVendedor());
		}
		dto.setNombre(vendedor.getNombre());
		dto.setApellido(vendedor.getApellido());
		dto.setCodigo(vendedor.getCodigo());
		dto.setDocumento(vendedor.getDocumento());
		dto.setUsuario(vendedor.getUsuario());
		dto.setFechaAlta(vendedor.getFechaAlta());
		dto.setFechaBaja(vendedor.getFechaBaja());
		dto.setFechaModificacion(vendedor.getFechaModificacion());

		return dto;
	}

}