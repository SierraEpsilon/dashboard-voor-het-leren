// parameters
var jsonURL = "test.json";
var divId = "chart1";
var catButton = "#catButton .ui-btn-text";
var graphButton = "#graphButton .ui-btn-text";
var graphDivID = "graphDiv";
// type to functions
graph = new Object();
graph.funcs = new Array();
graph.funcs["pie"] = new Array("Pie","pie");
graph.funcs["donut"] = new Array("Donut","donut");
graph.funcs["text"] = new Array("Text","text");
graph.funcs["scatter"] = new Array("Scatter","scatter");
// type handlers
function scatter(data){
	$("#"+graphDivID).html("");
	$.jqplot("graphDiv", data);
}
function text(data){
	$("#"+graphDivID).html("");
	$("#"+graphDivID).html(data);
}
function pie(data){
	$("#"+graphDivID).html("");
	$.jqplot(graphDivID, data,
		{
		  seriesDefaults: {
			// Make this a pie chart.
			renderer: jQuery.jqplot.PieRenderer,
			rendererOptions: {
			  // Put data labels on the pie slices.
			  // By default, labels show the percentage of the slice.
			  showDataLabels: true
			}
		  },
		  legend: { show:true, location: 'e' }
		}
	 );
}
function table(data){}

function getData(){
	$.getJSON(jsonURL,function(json){
		//graph methods and properties
		graph.count = 0;
		graph.cats = json;
		graph.getCat = function(){
			return this.cats[this.count];
		};
		graph.nextCat = function(){
			this.count++;
			if(this.count==this.cats.length) this.count=0;
			return this.cats[this.count];
		};
		for(i=0;i<graph.cats.length;i++){
			var validCount = -1;
			//remove unknown graph types
			for(j=0;j<graph.cats[i].graphs.length;j++){
				var testType = graph.cats[i].graphs[j].type;
				if(graph.funcs[testType]==null){
					graph.cats[i].graphs.splice(j,1);
					j--;
				}else{
					if(validCount==-1)
						validCount = j;
				}
			}
			//if none are left, add text type
			if(graph.cats[i].graphs.length==0){
				graph.cats[i].graphs[0] = new Object();
				graph.cats[i].graphs[0].type = "text";
				graph.cats[i].graphs[0].data = "No valid graphs included";
				validCount = 0;
			}
			//cat methods and properties
			graph.cats[i].count = validCount;
			graph.cats[i].getGraph = function(){
				return this.graphs[this.count];
			};
			graph.cats[i].nextGraph = function(){
				this.count++;
				if(this.count==this.graphs.length) this.count = 0;
				return this.graphs[this.count];
			};
		}
		$(catButton).text(graph.getCat().name);
		var curGraph = window.graph.getCat().getGraph();
		var type = curGraph.type;
		$(graphButton).text(graph.funcs[type][0]);
		eval(graph.funcs[type][1]+"(curGraph.data)");
	});
}

function changeCat(){
	oldCat = window.graph.getCat();
	newCat = window.graph.nextCat();
	//keep graph type if possible
	type = oldCat.getGraph().type;
	for(i=0;i<newCat.graphs.length;i++)
		if(newCat.graphs[i].type==type) newCat.count=i;
	$(catButton).text(newCat.name);
	newGraph = newCat.getGraph();
	var type = newGraph.type;
	$(graphButton).text(graph.funcs[type][0]);
	eval(graph.funcs[type][1]+"(newGraph.data)");
}

function changeGraph(){
	var nextGraph = window.graph.getCat().nextGraph();
	var type = nextGraph.type;
	$(graphButton).text(graph.funcs[type][0]);
	eval(graph.funcs[type][1]+"(nextGraph.data)");
}

$(document).ready(function(){
	getData();
});