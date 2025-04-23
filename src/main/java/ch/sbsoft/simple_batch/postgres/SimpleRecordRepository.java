package ch.sbsoft.simple_batch.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import ch.sbsoft.simple_batch.entity.SimpleRecord;

public interface SimpleRecordRepository extends JpaRepository<SimpleRecord, Long> {

}
