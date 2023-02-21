package baodientu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import baodientu.entity.BaiBao;

public interface IBaiBaoRepository extends JpaRepository<BaiBao, Integer>, JpaSpecificationExecutor<BaiBao> {
	
	@Query(nativeQuery = true, value = "SELECT COUNT(id) FROM BaiBao WHERE danhmuc_id = :danhmucId")
	public int countBaiBaoFromDanhMucId(@Param("danhmucId") int danhmucId);
}
