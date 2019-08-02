		var glj = ["-27", "-71", "-78", "-27", "-98", "-125", "-27", "-100", "-66"];
		var slj = ["-26", "-71", "-65", "-27", "-98", "-125", "-27", "-100", "-66"];
		var khslj = ["-27", "-113", "-81", "-27", "-101", "-98", "-26", "-108", "-74", "-27", "-98", "-125", "-27", "-100", "-66"];
		var yhlj = ["-26", "-100", "-119", "-27", "-82", "-77", "-27", "-98", "-125", "-27", "-100", "-66"];
		var flj=["-23", "-99", "-98", "-27", "-98", "-125","-27", "-100", "-66"];
		var map = {};
		map['.----'] = 1;
		map['..---'] = 2;
		map['...--'] = 3;
		map['....-'] = 4;
		map['.....'] = 5;
		map['-....'] = 6;
		map['--...'] = 7;
		map['---..'] = 8;
		map['----.'] = 9;
		map['-----'] = 0;

		function morseZpDecode(aa) {
			//var bytes = new Array();
			var text = aa.split("=");
			var bs = new Array();
			var id = 0; // bs 数组坐标
			var tex;
			for(var i = 0; i < text.length; i++) {
				if(text[i] == null || text[i].trim().length == 0)
					continue;

				tex = text[i].split(/[a-zA-Z]/);

				var te = "";
				if(tex[0].indexOf("*") != -1) {
					te = "-";
					tex[0] = tex[0].substring(1);
				}

				for(var j = 0; j < tex.length; j++) {
					if(tex[j] == null || tex[j].trim().length == 0)
						continue;
					te += map[tex[j]];
				}

				bs[id++] = te;

			}

			if(myequals(bs,glj)) {
				return 1;
			} else if(myequals(bs,slj)) {
				return 2;
			} else if(myequals(bs,khslj)) {
				return 3;
			} else if(myequals(bs,yhlj)) {
				return 4;
			}else if(myequals(bs,flj)) {
				return 5;
			}else {
				return 6;
			}

		}

	function myequals(arr1,arr2){
		if(arr1.length!==arr2.length) return false;
		for (var i=0;i<arr1.length;i++) {
			if(arr1[i]!==arr2[i])return false;
		}
		return true;
	}
	
