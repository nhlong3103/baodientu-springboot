package baodientu.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import baodientu.entity.DanhMuc;
import baodientu.form.CreateDanhMucForm;
import baodientu.form.DanhMucFilterForm;
import baodientu.form.UpdateDanhMucForm;

public interface IDanhMucService {

	public Page<DanhMuc> getAllDanhMucs(Pageable pageable, String search, DanhMucFilterForm filterForm);

	public DanhMuc getDanhMucById(int id);

	public DanhMuc getDanhMucByTenDanhMuc(String tenDanhMuc);
	
	public void createDanhMuc(CreateDanhMucForm form);

	public void updateDanhMuc(int id, UpdateDanhMucForm form);
	
	public void deleteDanhMuc(int id);
	
	public boolean isDanhMucExistsByTenDanhMuc(String name);

	public boolean isDanhMucExistsByID(Integer id);
}
