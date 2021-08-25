//import org.junit.Rule;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class SightingTest {
//
//    @Rule
//    public DatabaseRule database = new DatabaseRule();
//    public Sighting setupObject() {
//        return new Sighting(1, "Pembeni", "Patrick njoroge");
//    }
//
//    @Test
//    public void sighting_instantiatesCorrectly_true() {
//        assertTrue(setupObject() instanceof Sighting);
//    }
//    @Test
//    public void getAnimalId_sightingInstantiatesWithAnimalId_int() {
//        assertEquals(1, setupObject().getAnimalId());
//    }
//    @Test
//    public void getLocation_sightingInstantiatesWithLocation_string() {
//
//        assertEquals("Pembeni", setupObject().getLocation());
//
//    }
//    @Test
//    public void getRangerName_sightingInstantiatesWithRangerName_string() {
//        assertEquals("Patrick njoroge", setupObject().getRangerName());
//    }
//
//    @Test
//    public void equals_returnsTrueIfLocationAndAnimalIdAreSame_true() {
//        Sighting firstSighting = new Sighting(1, "Pembeni", "Dan");
//
//        Sighting secondSighting = new Sighting(1, "Pembeni", "Dan");
//
//        assertEquals(firstSighting, secondSighting);
//    }
//    @Test
//    public void save_insertsObjectIntoDatabase_Sighting() {
//        setupObject().save();
//        assertEquals(Sighting.all().get(0), setupObject());
//    }
//    @Test
//    public void save_assignsIdToObject() {
//        Sighting testSighting = new Sighting(1, "Pembeni", "Dan");
//
//        testSighting.save();
//
//        Sighting savedSighting = Sighting.all().get(0);
//
//        assertEquals(testSighting.getSightingId(), savedSighting.getSightingId());
//    }
//    @Test
//    public void all_returnsAllInstancesOfSighting_true() {
//        Sighting firstSighting = new Sighting(1, "pembeni", "Dan");
//        firstSighting.save();
//
//        Sighting secondSighting = new Sighting(56, "Pwani", "Dice");
//        secondSighting.save();
//
//       // assertEquals(true, Sighting.all().get(0).equals(firstSighting));
//
//        assertEquals(Sighting.all().get(1), secondSighting);
//    }
//    @Test
//    public void find_returnsSightingWithSameId_secondSighting() {
//        Sighting firstSighting = new Sighting(1, "pembeni", "Dan");
//        firstSighting.save();
//
//        Sighting secondSighting = new Sighting(2, "Pwani", "Dice");
//        secondSighting.save();
//
//        assertEquals(Sighting.find(secondSighting.getSightingId()), secondSighting);
//    }
//}