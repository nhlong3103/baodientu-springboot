package baodientu.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import baodientu.entity.BaiBao;
import baodientu.entity.DanhMuc;
import baodientu.form.BaiBaoFilterForm;
import baodientu.form.CreateBaiBaoForm;
import baodientu.form.UpdateBaiBaoForm;
import baodientu.repository.IBaiBaoRepository;
import baodientu.repository.IDanhMucRepository;
import baodientu.specification.baibao.BaiBaoSpecification;

@Service
public class BaiBaoService implements IBaiBaoService {

	@Autowired
	private IBaiBaoRepository repository;

	@Autowired
	private IDanhMucRepository danhMucRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Page<BaiBao> getAllBaiBaos(Pageable pageable, String search, BaiBaoFilterForm filterForm) {
		Specification<BaiBao> where = BaiBaoSpecification.buildWhere(search, filterForm);
		return repository.findAll(where, pageable);
	}

	@Override
	public BaiBao getBaiBaoById(int id) {
		return repository.findById(id).get();
	}

	@Override
	public BaiBao getBaiBaoByTieuDe(String tieuDe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBaiBao(CreateBaiBaoForm form) {
		// omit id field
		TypeMap<CreateBaiBaoForm, BaiBao> typeMap = modelMapper.getTypeMap(CreateBaiBaoForm.class, BaiBao.class);
		if (typeMap == null) { // if not already added
			// skip field
			modelMapper.addMappings(new PropertyMap<CreateBaiBaoForm, BaiBao>() {
				@Override
				protected void configure() {
					skip(destination.getId());
				}
			});
		}

		// convert form to entity
		BaiBao baiBaoEntity = modelMapper.map(form, BaiBao.class);

		// create bai bao
		BaiBao baiBao = repository.save(baiBaoEntity);

		DanhMuc danhMucUpdate = danhMucRepository.findById(form.getDanhMucId()).get();

		danhMucUpdate.setSlBaiViet(repository.countBaiBaoFromDanhMucId(form.getDanhMucId()));
		repository.save(baiBao);

	}

	@Override
	public void updateBaiBao(int id, UpdateBaiBaoForm form) {
		form.setId(id);
		// convert form to entity
		BaiBao baiBao = modelMapper.map(form, BaiBao.class);
		repository.save(baiBao);
	}

	@Override
	public void deleteBaiBaoById(int id) {
		repository.deleteById(id);
	}

}
