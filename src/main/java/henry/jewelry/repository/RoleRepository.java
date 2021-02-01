package henry.jewelry.repository;

import henry.jewelry.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Role findRoleByName(String roleName);

	@Override
	void delete(Role role);

}