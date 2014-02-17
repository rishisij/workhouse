import java.io.File;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.eval.DataModelBuilder;
import org.apache.mahout.cf.taste.eval.IRStatistics;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.eval.RecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.common.FastByIDMap;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.GenericRecommenderIRStatsEvaluator;
import org.apache.mahout.cf.taste.impl.model.GenericBooleanPrefDataModel;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.model.PreferenceArray;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

class RecommenderIntro {
public static void main(String[] args) throws Exception 
    { 
		/*File fl= new File("intro.csv");
    	DataModel model = new FileDataModel (fl);
    	UserSimilarity similarity = new PearsonCorrelationSimilarity (model);
    	UserNeighborhood neighborhood = new NearestNUserNeighborhood (2, similarity, model);
    	Recommender recommender = new GenericUserBasedRecommender (model, neighborhood, similarity);
    	List<RecommendedItem> recommendations = recommender.recommend(1, 1);
    	for (RecommendedItem recommendation : recommendations) 
    	{
    		
    		System.out.println(recommendation);
    		
        }*/
		/*RandomUtils.useTestSeed();
		
		DataModel model = new FileDataModel (new File("intro.csv"));

		RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator ();
		System.out.println(model);
		RecommenderBuilder builder = new RecommenderBuilder() {
			
			@Override
			public Recommender buildRecommender(DataModel model)throws TasteException
			{
				UserSimilarity similarity = new PearsonCorrelationSimilarity (model);
				System.out.println(similarity);
				UserNeighborhood neighborhood = new NearestNUserNeighborhood (2, similarity, model);
				System.out.println(neighborhood);
					return new GenericUserBasedRecommender (model, neighborhood, similarity);
			}
		};
		
		double score = evaluator.evaluate(builder, null, model, 0.7, 1.0);
		
		System.out.println(score);

*//*			
	File fl= new File("/home/gateway/Downloads/ml-100k/ua.base");
	DataModel model = new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(new FileDataModel(fl)));
	System.out.println(model);
			RecommenderEvaluator evaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
			RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
				public Recommender buildRecommender(DataModel model)throws TasteException {
					UserSimilarity similarity =new LogLikelihoodSimilarity(model);
					UserNeighborhood neighborhood = new NearestNUserNeighborhood(4, similarity, model);
					return new GenericUserBasedRecommender(model, neighborhood, similarity);
				}
			};
			System.out.println(recommenderBuilder);
			DataModelBuilder modelBuilder = new DataModelBuilder() {
				public DataModel buildDataModel( FastByIDMap<PreferenceArray> trainingData) {
					return new GenericBooleanPrefDataModel( GenericBooleanPrefDataModel.toDataMap(trainingData));
				}
			};
			double score = evaluator.evaluate(recommenderBuilder, modelBuilder, model, 0.9, 1.0);
			System.out.println(score);
*/
	@SuppressWarnings("deprecation")
	DataModel model = new GenericBooleanPrefDataModel(new FileDataModel(new File("/home/gateway/Downloads/ml-100k/ua.base")));
	RecommenderIRStatsEvaluator evaluator = new GenericRecommenderIRStatsEvaluator();
	RecommenderBuilder recommenderBuilder = new RecommenderBuilder() {
	@Override
	public Recommender buildRecommender(DataModel model) throws TasteException {
	UserSimilarity similarity = new LogLikelihoodSimilarity(model);
	UserNeighborhood neighborhood = new NearestNUserNeighborhood(10, similarity, model);
	return new GenericUserBasedRecommender(model, neighborhood, similarity);
	}
	};
	DataModelBuilder modelBuilder = new DataModelBuilder() {
	@Override
	public DataModel buildDataModel(FastByIDMap<PreferenceArray> trainingData) {
		return new GenericBooleanPrefDataModel(GenericBooleanPrefDataModel.toDataMap(trainingData));
	}
	};
	IRStatistics stats = evaluator.evaluate(recommenderBuilder, modelBuilder, model, null, 10,GenericRecommenderIRStatsEvaluator.CHOOSE_THRESHOLD,1.0);
	System.out.println(stats.getPrecision());
	System.out.println(stats.getRecall());
    }

}