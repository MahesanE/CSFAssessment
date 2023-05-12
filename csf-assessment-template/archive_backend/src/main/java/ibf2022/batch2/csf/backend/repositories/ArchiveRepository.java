package ibf2022.batch2.csf.backend.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch2.csf.backend.models.Archive;

@Repository
public class ArchiveRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	private final String COLLECTION_NAME = "archives";

	// TODO: Task 4
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method

	/*
	 * db.collection.insertOne({
	 * bundleId: UUID().toString().substring(0, 8),
	 * date: new Date(),
	 * name: "<name>",
	 * title: "<title>",
	 * comments: "<comments>",
	 * urls: ["url1", "url2", "url3"]
	 * })
	 */
	public Archive recordBundle(String name, String title, String comments, List<String> urls) {
		Archive archive = new Archive();
		archive.setBundleId(UUID.randomUUID().toString().substring(0, 8));
		archive.setDate(new Date());
		archive.setName(name);
		archive.setTitle(title);
		archive.setComments(comments);
		archive.setUrls(urls);

		return mongoTemplate.insert(archive, COLLECTION_NAME);
	}

	// TODO: Task 5
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method

	/*
	 * db.getCollection("archives").find({_id: "273ef5d6"})
	 */

	public Archive getBundleByBundleId(String bundleId) {
		Query query = new Query(Criteria.where("bundleId").is(bundleId));

		return mongoTemplate.findOne(query, Archive.class, COLLECTION_NAME);
	}

	// TODO: Task 6
	// You are free to change the parameter and the return type
	// Do not change the method's name
	// Write the native mongo query that you will be using in this method

	/*
	 * db.getCollection("archives").aggregate([
	 * { $sort: { date: -1 } },
	 * { $sort: { title: 1 } }
	 * ])
	 */

	public List<Archive> getBundles() {
		Aggregation agg = Aggregation.newAggregation(
				Aggregation.sort(Sort.Direction.DESC, "date"),
				Aggregation.sort(Sort.Direction.ASC, "title"));

		AggregationResults<Archive> results = mongoTemplate.aggregate(agg, COLLECTION_NAME, Archive.class);
		return results.getMappedResults();
	}

}
