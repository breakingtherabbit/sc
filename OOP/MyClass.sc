MyClass : Object {

	instanceMethod { | argument |
		this.anotherInstanceMethod(argument)
	}

	anotherInstanceMethod { | argument |
		"Hello instance".postln
	}

}