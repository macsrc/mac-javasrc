sealed class C1 permits C2, C3 {}	// Only 2 direct subclasses
final class C2 extends Top {}		// cannot be subclassed
non-sealed class C3 extends Top {}	// Valid subclass, wide open for subclassing!
class C4 extends C3 {}				// Subclass of C3
class SomeRandomUserClass extends C3 {} // Another subclass of C3

