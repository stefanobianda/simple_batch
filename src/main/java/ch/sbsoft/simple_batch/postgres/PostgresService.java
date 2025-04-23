package ch.sbsoft.simple_batch.postgres;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.sbsoft.simple_batch.entity.SimpleRecord;

@Service
public class PostgresService {

    @Autowired
    private SimpleRecordRepository repository;

    public void saveSimpleRecord(String valore) {
    	SimpleRecord simpleRecord = new SimpleRecord();
    	simpleRecord.setRecord(valore);
        repository.save(simpleRecord);
    }

	public List<String> getAllSimpleRecord() {
		List<SimpleRecord> simpleRecords = repository.findAll();
		return simpleRecords.stream().map(SimpleRecord::getRecord).collect(Collectors.toList());
	}

	public String getSimpleRecord() {
		String record = null;
		if (repository.count() > 0) {
			SimpleRecord simpleRecord = repository.findAll().get(0);
			record = simpleRecord.getRecord();
			repository.delete(simpleRecord);
		}
		return record;
	}

}
