package baodientu.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import baodientu.entity.DanhMuc;
import baodientu.form.CreateDanhMucForm;
import baodientu.form.DanhMucFilterForm;
import baodientu.form.UpdateDanhMucForm;
import baodientu.repository.IDanhMucRepository;
import baodientu.specification.danhmuc.DanhMucSpecification;

@Service
public class DanhMucService implements IDanhMucService {

	@Autowired
	private IDanhMucRepository repository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<DanhMuc> getAllDanhMucs(Pageable pageable, String search, DanhMucFilterForm filterForm) {
		Specification<DanhMuc> where = DanhMucSpecification.buildWhere(search, filterForm);
		return repository.findAll(where, pageable);
	}

	@Override
	public DanhMuc getDanhMucById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public DanhMuc getDanhMucByTenDanhMuc(String tenDanhMuc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createDanhMuc(CreateDanhMucForm form) {
		// convert form to entity
		DanhMuc danhmucEntity = modelMapper.map(form, DanhMuc.class);

		// create department
		DanhMuc danhmuc = repository.save(danhmucEntity);
		repository.save(danhmuc);

	}

	@Override
	public void updateDanhMuc(int id, UpdateDanhMucForm form) {
		form.setId(id);
		DanhMuc danhmuc = modelMapper.map(form, DanhMuc.class);
		repository.save(danhmuc);

	}

	@Override
	public void deleteDanhMuc(int id) {
		repository.deleteById(id);
	}

	@Override
	public boolean isDanhMucExistsByTenDanhMuc(String name) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isDanhMucExistsByID(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
