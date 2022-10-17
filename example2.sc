{
	[SinOsc.ar(440, 0, 0.2), SinOsc.ar(442, 0, 0.2)]
}.play;


{SinOsc.ar(440, 0, 0.2)}.play;

"Hello World!".postln;

(
"Call me,".postln;
"Ishmael.".postln;
)

[
"Call me?".postln,
"Ishmael.".postln
]

/*

*/

// Booting Server
s.boot; // CTRL + B

// Quiting Server
s.quit;

f = { "Teste".postln; };

f.value;

(
{
	var ampOsc;
	ampOsc = SinOsc.kr(0.5, 1.5pi, 0.5, 0.5);
	SinOsc.ar(440, 0, ampOsc);
}.play;
)

a = ["foo", "bar"];
a.at(2);

{ SinOsc.ar([523.3, 659.3, 784], 0, 0.2) }.plot;

{ Saw.ar(XLine.kr(20000,200,6),0.2) }.scope(1);

{ WhiteNoise.ar(0.2) }.scope(1);

(
SynthDef(\EightBitSaw, {|freq = 440, dur = 1, amp = 0.2, out = 0|
	var osc, env, mask;

	env = EnvGen.kr(Env.linen(dur * 0.1, dur * 0.8, dur * 0.1, amp, 0), doneAction: 2);
	osc = Saw.ar(freq, env);
	mask = MantissaMask.ar(osc, 8); // make 8 bit
	Out.ar(out, mask);
}).play
)




(

SynthDef(\kivBass) { |out=0, freq=100, gate=1, imp=0.125, pan=0, amp=0.5|
	var sust = Linen.kr(gate, doneAction: 2),
		vSaw = VarSaw.ar(freq, 0, LFTri.kr(imp).range(0.88, 0.98), amp),
		pan2 = Pan2.ar(vSaw, pan);

	Out.ar(out, pan2*sust);
}.send(s);

Routine({
	var freq = Pseq([100, 75], inf).asStream,
		dur  = Pseq([4], inf).asStream;

	x = Synth(\kivBass, [\freq, freq.next]);

	loop({
		dur.next.wait;
		x.set(\freq, freq.next);
	}).play;
}).play;

)