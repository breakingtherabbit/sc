// Examples

// Simple FM
{ SinOsc.ar(SinOsc.kr([1, 3]).exprange(100, 2e3), 0, 0.2) }.play

// Drummer – Thor Magnusson, 2006
{
  var snare, bdrum, hihat;
  var tempo = 4;

  tempo = Impulse.ar(tempo); // for a drunk drummer replace Impulse with Dust !!!
  snare = WhiteNoise.ar(Decay2.ar(PulseDivider.ar(tempo, 4, 2), 0.005, 0.5));
  bdrum = SinOsc.ar(Line.ar(120,60, 1), 0, Decay2.ar(PulseDivider.ar(tempo, 4, 0), 0.005, 0.5));
  hihat = HPF.ar(WhiteNoise.ar(1), 10000) * Decay2.ar(tempo, 0.005, 0.5);

  Out.ar(0, (snare + bdrum + hihat) * 0.4 ! 2)
}.play

// Chainsaw – Julian Rohrhuber, 2007
(
  var f, g;

  f = { | saw |
    var freq, u, amp, rate;

    rate = ExpRand(0.1, 2);
    freq = if (0.6.coin) {
      LFNoise1.kr(rate.dup).exprange(0.01, 10)
    } {
      LFNoise1.kr(rate.dup).exprange(10, 50)
    };
    u = LFSaw.kr(LinExp.kr(saw, -1, 1, freq, freq * LFNoise1.kr(rate.dup).exprange(2, 10)));
    u = if (0.5.coin) {
      u * [1 - saw, saw.reverse].choose
    } {
      u * LFSaw.kr(freq * 0.1, 0, 0.1, 1)
    };

    u.clip2(1.0)
  };

  g = { | func, n = 5 |
    n.do { func = func <> func };
    func
  };

  {
    var freq;
    freq = g.(f, 4).value(LFSaw.kr(0.2 * [1, 1.1])).exprange(6, 11000);
    BPF.ar(Saw.ar(freq).product, [70, 800, 9000, 5242], 0.2).sum.dup * 0.3
  }.play
)