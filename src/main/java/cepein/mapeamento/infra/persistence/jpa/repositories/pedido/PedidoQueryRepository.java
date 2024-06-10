package cepein.mapeamento.infra.persistence.jpa.repositories.pedido;

import cepein.mapeamento.infra.persistence.jpa.entities.pedido.JpaPedidoQueryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PedidoQueryRepository extends JpaRepository<JpaPedidoQueryEntity, Long> {
    Optional<JpaPedidoQueryEntity> findByUuid(String uuid);
}
