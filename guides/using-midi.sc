s.boot;

(
var notes, on, off;

MIDIClient.init;
MIDIIn.connectAll;

notes = Array.newClear(128);

on = MIDIFunc.noteOn({ | vel, num, chan, src |
	notes[num] = Synth(\default, [\freq, num.midicps, \amp, vel * 0.00315]);
});

off = MIDIFunc.noteOff({ | vel, num, chan, src |
	notes[num].release;
});

q = { on.free; off.free; };
)

q.value;