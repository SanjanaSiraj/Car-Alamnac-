package sample;

import Utils.*;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable{
    private int state=1;
    private Socket s;
    private Main m;
    Thread t;
    ReadThread(Socket s,Main m){
        this.s = s;
        this.m = m;
        t = new Thread(this);
        t.start();
    }
    @Override
    public void run() {
    Object obj = null;
    try {
        while (true) {
            ObjectInputStream ois = null;
            try {
                ois = new ObjectInputStream(s.getInputStream());
                obj = ois.readUnshared();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            if (obj != null) {
                if (obj instanceof LoginData) {
                    LoginData ld = (LoginData) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (ld.getStatus()) {
                                if (ld.getUsername().equals("viewer")) {
                                    try {
                                        state = 2;
                                        m.ViewerHomePage();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } else {
                                    try {
                                        state = 3;
                                        m.ManufacturerHomePage();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else m.showAlert();
                        }
                    });
                }
                if (obj instanceof LogOutData) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 1;
                                m.LoginPage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof List) {
                    List<Car> carList = (List) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 4;
                                m.ViewAllCarsPage(carList);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof AddCarData) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 5;
                                m.AddCarPage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof DeleteCarData) {
                    DeleteCarData dcd = (DeleteCarData) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 8;
                                m.DeleteCarPage(dcd.getCarList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof ReturnDataManufacturer) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 3;
                                m.ManufacturerHomePage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof ReturnDataViewer) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 2;
                                m.ViewerHomePage();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof AddCarToDataBaseData) {
                    AddCarToDataBaseData acdb = (AddCarToDataBaseData) obj;
                    if (acdb.isStatus()) {
                        Refresh refresh = new Refresh();
                        refresh.setStatus(true);
                        try {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    m.AddAlert();
                                }
                            });
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                            objectOutputStream.writeUnshared(refresh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.FailedtoAddAlert();
                            }
                        });
                    }
                }
                if (obj instanceof DeleteCarFromDatabase) {
                    DeleteCarFromDatabase dcfd = (DeleteCarFromDatabase) obj;
                    if (dcfd.isStatus()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.DeleteAlert();
                            }
                        });
                        Refresh refresh = new Refresh();
                        refresh.setdeleteStatus(true);
                        refresh.setStatus(true);
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                            objectOutputStream.writeUnshared(refresh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.FailedtoDeleteAlert();
                            }
                        });
                    }
                }
                if (obj instanceof Refresh) {
                    Refresh refresh = (Refresh) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (state == 4) {
                                try {
                                    m.ViewAllCarsPage(refresh.getCarList());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (state == 5) {
                                viewAllCarsData vacd = new viewAllCarsData();
                                vacd.setStatus(true);
                                vacd.setPrevious("AddCar");
                                try {
                                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                                    oos.writeUnshared(vacd);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else if (state == 8) {
                                if (refresh.getdeleteStatus()) {
                                    try {
                                        m.DeleteCarPage(refresh.getCarList());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (state == 9) {
                                if (refresh.isbuyStatus()) {
                                    try {
                                        m.BuyCarPage(refresh.getCarList());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            } else if (state == 10) {
                                if (refresh.isEditStatus()) {
                                    try {
                                        m.EditCarPage(refresh.getCarList());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    });
                }
                if (obj instanceof viewCarByReg) {
                    viewCarByReg vcbr = (viewCarByReg) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 7;
                                m.GetRegNumPage(vcbr.getCarList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof viewCarByMakeAndModel) {
                    viewCarByMakeAndModel vcbmm = (viewCarByMakeAndModel) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 6;
                                m.GetMakeAndModelPage(vcbmm.getCarList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof BuyCarData) {
                    BuyCarData bcd = (BuyCarData) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                state = 9;
                                m.BuyCarPage(bcd.getCarList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof EditCarData) {
                    EditCarData ecd = (EditCarData) obj;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            state = 10;
                            try {
                                m.EditCarPage(ecd.getCarList());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                if (obj instanceof EditCarInDatabase) {
                    EditCarInDatabase ecid = (EditCarInDatabase) obj;
                    if (!ecid.isStatus()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.FailedtoAddAlert();
                            }
                        });
                    } else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.EditAlert();
                            }
                        });
                        Refresh refresh = new Refresh();
                        refresh.setEditStatus(true);
                        refresh.setStatus(true);
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                            objectOutputStream.writeUnshared(refresh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (obj instanceof TransactionData) {
                    TransactionData td = (TransactionData) obj;
                    if (!td.isStatus()) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.FailedBuyAlert();
                            }
                        });
                    }
                    else {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                m.BuyAlert();
                            }
                        });
                    }
                        Refresh refresh = new Refresh();
                        refresh.setbuyStatus(true);
                        refresh.setStatus(true);
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(s.getOutputStream());
                            objectOutputStream.writeUnshared(refresh);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                 }
            }
        } catch (Exception e) {
             e.printStackTrace();
         }finally {
             try {
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
    }
}




