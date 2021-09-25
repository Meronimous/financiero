package com.contaduria.movimientofinanciero.specifications;

import com.contaduria.movimientofinanciero.entities.FundRequest;
import com.contaduria.movimientofinanciero.entities.Movement;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

@And({
        @Spec(path ="movementCode", spec = Equal.class),
        @Spec(path ="description", spec = LikeIgnoreCase.class),
        @Spec(path ="certificateNumber", spec = Equal.class),
        @Spec(path ="numOrder", spec = Equal.class),
        @Spec(path ="year", spec = Equal.class),
        @Spec(path ="codeOrganism", spec = Equal.class),
        @Spec(path ="orderCod", spec = Equal.class),
        @Spec(path ="fundClass", spec = Equal.class),
        @Spec(path ="imputationCode", spec = Equal.class),
        @Spec(path ="movementAmount", spec = Equal.class)}
)
public interface MovementSpecification extends Specification<Movement>  {
}