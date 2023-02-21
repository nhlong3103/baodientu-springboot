package baodientu.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import baodientu.dto.DanhMucDTO;
import baodientu.entity.DanhMuc;
import baodientu.form.CreateDanhMucForm;
import baodientu.form.DanhMucFilterForm;
import baodientu.form.UpdateDanhMucForm;
import baodientu.service.IDanhMucService;

@RestController
@RequestMapping(value = "api/baodientu-springboot/danhmucs")
@CrossOrigin("*")
public class DanhMucController {

	@Autowired
	private IDanhMucService service;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping()
	public Page<DanhMucDTO> getAllDanhMucs(Pageable pageable,
			@RequestParam(name = "search", required = false) String search, DanhMucFilterForm filterForm) {
		Page<DanhMuc> entityPages = service.getAllDanhMucs(pageable, search, filterForm);

		// convert entities --> dtos
		List<DanhMucDTO> dtos = modelMapper.map(entityPages.getContent(), new TypeToken<List<DanhMucDTO>>() {
		}.getType());

		Page<DanhMucDTO> dtoPages = new PageImpl<>(dtos, pageable, entityPages.getTotalElements());

		return dtoPages;
	}
	
	@PostMapping()
	public void createDanhMuc(@RequestBody @Valid CreateDanhMucForm form) {
		service.createDanhMuc(form);
	}

	@PutMapping(value = "/{id}")
	public void updateDanhMuc(@PathVariable(name = "id") int id, @RequestBody @Valid UpdateDanhMucForm form) {
		service.updateDanhMuc(id, form);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteDanhMuc(@PathVariable(name = "id") int id) {
		service.deleteDanhMuc(id);
	}
}
